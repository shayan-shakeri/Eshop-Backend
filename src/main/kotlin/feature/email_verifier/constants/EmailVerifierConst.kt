package com.shayan.feature.sender.constants

object EmailVerifierConst {

    const val TABLE_NAME = "verification_codes"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val EMAIL = "email"
    const val CODE_HASH = "code_hash"
    const val EXPIRES_AT = "expires_at"
    const val USED = "used"
    const val CREATED_AT = "created_at"

    const val EMAIL_LENGTH = 255
    const val CODE_HASH_LENGTH = 512

    const val CODE_LENGTH = 6
    const val EXPIRATION_MINUTES = 2 * 60

    const val MAIN_ROUTE = "/verify-email"
    const val SEND_ROUTE = "/send"
    const val VERIFY_ROUTE = "/verify"

    const val SEND_ACTION = "User requested to get verification code"
    const val VERIFY_ACTION = "User verified the verification code"

    const val FROM = "onboarding@resend.dev"
    const val SUBJECT = "Email verification"
}