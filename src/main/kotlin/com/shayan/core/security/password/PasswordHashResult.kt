package core.security.password

data class PasswordHashResult(

    val hash: String,

    val salt: String,

    val iterations: Int,

    val algorithm: String

)