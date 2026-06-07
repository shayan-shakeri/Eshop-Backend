package core.consts

object AppConstants {

    object Env {

        const val DB_URL = "DB_URL"
        const val DB_USER = "DB_USER"
        const val DB_PASSWORD = "DB_PASSWORD"

        const val DB_POOL_SIZE = "DB_POOL_SIZE"

        const val HASH_ITERATIONS = "HASH_ITERATIONS"
        const val HASH_ALGORITHM = "HASH_ALGORITHM"

        const val ACCESS_SECRET = "ACCESS_TOKEN_SECRET"
        const val REFRESH_SECRET = "REFRESH_TOKEN_SECRET"

        const val TOKEN_ISSUER = "TOKEN_ISSUER"
        const val TOKEN_AUDIENCE = "TOKEN_AUDIENCE"

        const val ACCESS_USER_EXP = "ACCESS_USER_EXP"
        const val ACCESS_EMPLOYEE_EXP = "ACCESS_EMPLOYEE_EXP"
        const val REFRESH_EXP = "REFRESH_EXP"

        const val EMAIL_SENDER_API_KEY = "EMAIL_SENDER_API_KEY"
    }

    object Jwt {

        const val CLAIM_ID = "id"
        const val CLAIM_ROLE = "role"
        const val CLAIM_EMPLOYEE = "employee"
        const val CLAIM_JTI = "jti"
        const val ACCESS_AUTH = "access-auth"
    }

    object Crypto {

        const val RANDOM_ALGORITHM = "SHA1PRNG"

    }

    object Exception{
        const val MISSING_PARAM = "Missing parameter"

        const val INVALID_TOKEN = "Invalid token"

        const val UNAUTHORIZED = "Unauthorized"
    }

    object NumeralConst{
        const val ID_LENGTH = 40
    }
}

typealias ANC = AppConstants.NumeralConst
typealias ENV = AppConstants.Env
typealias CJWT = AppConstants.Jwt
typealias CRY = AppConstants.Crypto
typealias EXC = AppConstants.Exception
