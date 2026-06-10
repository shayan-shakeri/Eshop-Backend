package com.shayan.feature.search_history.route

import com.shayan.feature.search_history.constants.SearchHistoryConstant
import com.shayan.feature.search_history.service.SearchHistoryService
import com.shayan.util.callUtil.idExtractor
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.searchHistoryRoute(
    searchHistoryService: SearchHistoryService
) {
    route(SearchHistoryConstant.MAIN_ROUTE) {
        authenticate(CJWT.ACCESS_AUTH) {
            get(SearchHistoryConstant.READ_ROUTE) {
                val id = call.idExtractor()
                call.respond(searchHistoryService.readHistory(id))
            }

            post(SearchHistoryConstant.ADD_ROUTE) {
                val id = call.idExtractor()
                val request = call.receive<String>()
                call.respond(searchHistoryService.addSearchHistory(userId = id, content = request))
            }

            delete(SearchHistoryConstant.DELETE_ROUTE) {
                val id = call.idExtractor()
                val ip = call.extractFromParam(SearchHistoryConstant.IP_PARM)
                call.respond(searchHistoryService.deleteSearchHistory(id, ip))
            }
        }
    }
}