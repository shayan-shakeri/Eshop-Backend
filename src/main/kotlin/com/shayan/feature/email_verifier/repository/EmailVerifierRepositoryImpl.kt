package com.shayan.feature.email_verifier.repository

import com.shayan.feature.email_verifier.model.EmailVerifier
import com.shayan.feature.email_verifier.mapper.toEmailVerifier
import com.shayan.feature.email_verifier.table.EmailVerifierTable
import core.database.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import java.time.Instant


class EmailVerifierRepositoryImpl : EmailVerifierRepository {

    override suspend fun create(entity: EmailVerifier): EmailVerifier = dbQuery {

        EmailVerifierTable.insert {
            it[id] = entity.id
            it[email] = entity.email
            it[codeHash] = entity.codeHash
            it[expiresAt] = entity.expiresAt
            it[used] = entity.used
            it[createdAt] = entity.createdAt
        }

        entity
    }

    override suspend fun findLatestByEmail(email: String): EmailVerifier? = dbQuery {
        EmailVerifierTable
            .selectAll()
            .where { EmailVerifierTable.email eq email }
            .orderBy(EmailVerifierTable.createdAt, SortOrder.DESC)
            .limit(1)
            .singleOrNull()
            ?.toEmailVerifier()
    }

    override suspend fun markAsUsed(id: String): Boolean = dbQuery {
        EmailVerifierTable.update({ EmailVerifierTable.id eq id }) {
            it[used] = true
        } > 0
    }

    override suspend fun deleteExpired(): Unit= dbQuery {
        val now = Instant.now()

        EmailVerifierTable.deleteWhere {
            EmailVerifierTable.expiresAt lessEq now
        }
    }
}