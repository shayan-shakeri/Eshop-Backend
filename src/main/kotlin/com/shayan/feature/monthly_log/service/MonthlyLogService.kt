package com.shayan.feature.monthly_log.service

import com.shayan.core.exception.AlreadyExistsException
import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.employee.service.EmployeeService
import com.shayan.feature.monthly_log.dto.MonthlyLogResponse
import com.shayan.feature.monthly_log.mapper.toMonthlyLogResponse
import com.shayan.feature.monthly_log.model.MonthlyLog
import com.shayan.feature.monthly_log.repository.MonthlyLogRepository
import com.shayan.feature.order_product.service.OrderProductService
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId

class MonthlyLogService(
    private val repository: MonthlyLogRepository,
    private val orderProductService: OrderProductService,
    private val employeeService: EmployeeService
) {

    suspend fun generate(
        month: Int,
        year: Int
    ): MonthlyLogResponse =
        dbQuery {

            repository.findByMonthAndYear(
                month,
                year
            )?.let {
                throw AlreadyExistsException()
            }

            val orderProducts =
                orderProductService.readAll()

            val employees =
                employeeService.getAll()

            val revenue =
                orderProducts.sumOf {
                    it.finalPrice
                }

            val goods =
                orderProducts.sumOf {
                    it.originalPrice
                }

            val salary =
                employees.sumOf { it.salary }

            val expenses =
                goods + salary

            var profit =
                revenue - expenses

            val previousDebt =
                getPreviousDebt(
                    month,
                    year
                )

            var debt =
                previousDebt

            if (
                profit < BigDecimal.ZERO
            ) {

                debt += profit.abs()

                profit =
                    BigDecimal.ZERO
            } else if (
                debt > BigDecimal.ZERO
            ) {

                if (
                    profit >= debt
                ) {

                    profit -= debt

                    debt =
                        BigDecimal.ZERO
                } else {

                    debt -= profit

                    profit =
                        BigDecimal.ZERO
                }
            }

            val monthlyLog =
                MonthlyLog(
                    id = IdGenerator.generate(),

                    month = month,
                    year = year,

                    goods = goods,
                    salary = salary,

                    insurance =
                        BigDecimal.ZERO,

                    debt = debt,

                    debtPaid =
                        debt == BigDecimal.ZERO,

                    other =
                        BigDecimal.ZERO,

                    finalExpenses =
                        expenses,

                    finalProfit =
                        profit,

                    totalSum =
                        revenue,

                    createdAt =
                        Instant.now()
                )

            repository.add(
                monthlyLog
            )?.toMonthlyLogResponse()
                ?: throw FailedToAdd()
        }

    private suspend fun getPreviousDebt(
        month: Int,
        year: Int
    ): BigDecimal {

        val previousMonth =
            if (month == 1)
                12
            else
                month - 1

        val previousYear =
            if (month == 1)
                year - 1
            else
                year

        return repository
            .findByMonthAndYear(
                previousMonth,
                previousYear
            )?.debt
            ?: BigDecimal.ZERO
    }

    suspend fun readAll():
            List<MonthlyLogResponse> =
        dbQuery {

            repository.readAll()
                .map {
                    it.toMonthlyLogResponse()
                }
        }

    suspend fun readById(
        id: String
    ): MonthlyLogResponse =
        dbQuery {

            repository.findById(
                id
            )?.toMonthlyLogResponse()
                ?: throw NotFoundException()
        }


    suspend fun readCurrent():
            MonthlyLogResponse =
        dbQuery {

            val now =
                Instant.now()
                    .atZone(
                        ZoneId.systemDefault()
                    )

            repository.findByMonthAndYear(
                month = now.monthValue,
                year = now.year
            )?.toMonthlyLogResponse()
                ?: throw NotFoundException()
        }
}