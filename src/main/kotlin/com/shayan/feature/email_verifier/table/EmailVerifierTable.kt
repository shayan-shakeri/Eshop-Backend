package com.shayan.feature.email_verifier.table

import com.shayan.feature.email_verifier.constants.EmailVerifierConst
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object EmailVerifierTable : Table(EmailVerifierConst.TABLE_NAME) {

    val id = varchar(EmailVerifierConst.ID, ANC.ID_LENGTH)

    val email = varchar(EmailVerifierConst.EMAIL, EmailVerifierConst.EMAIL_LENGTH)

    val codeHash = varchar(EmailVerifierConst.CODE_HASH, EmailVerifierConst.CODE_HASH_LENGTH)

    val expiresAt = timestamp(EmailVerifierConst.EXPIRES_AT)

    val used = bool(EmailVerifierConst.USED).default(false)

    val createdAt = timestamp(EmailVerifierConst.CREATED_AT)

    override val primaryKey = PrimaryKey(id)
}