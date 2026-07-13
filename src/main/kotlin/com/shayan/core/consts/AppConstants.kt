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
        const val ENUM_LENGTH = 20
    }

    object Role{
        const val CEO = 0
        const val CUSTOMER_SUPPORT = 1
        const val STORAGE = 2
        const val DEVELOPER = 2
        const val HR = 4
        const val MARKETING = 5
    }

    object StatusPages{
        const val UNAUTHORIZED = "Unauthorized"
        const val INVALID_CREDENTIALS = "Invalid credentials"
        const val FORBIDDEN = "Forbidden"
        const val NOT_FOUND = "Not found"
        const val BAD_REQUEST = "Bad Request"
        const val EMAIL_EXISTS = "Email exists"
        const val IMAGE_EXISTS = "Image exists"
        const val FAILED_TO_ADD = "Failed To Add"
        const val ALREADY_EXISTS = "Already Exists"
    }
}

typealias ANC = AppConstants.NumeralConst
typealias ENV = AppConstants.Env
typealias CJWT = AppConstants.Jwt
typealias CRY = AppConstants.Crypto
typealias EXC = AppConstants.Exception
typealias ACR = AppConstants.Role
typealias ASP = AppConstants.StatusPages