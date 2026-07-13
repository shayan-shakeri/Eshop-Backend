package core.security.password

import core.consts.AppConstants
import core.consts.CRY
import core.consts.ENV
import core.util.EnvLoader
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object PasswordHasher {

    fun hashPassword(
        password: String,
        salt: String? = null,
        iterations: Int? = null,
        algorithm: String? = null
    ): PasswordHashResult {

        val usedAlgorithm =
            algorithm ?: EnvLoader.get(ENV.HASH_ALGORITHM)

        val usedIterations =
            iterations ?: EnvLoader.getInt(ENV.HASH_ITERATIONS)

        val saltBytes = salt?.let {
            Base64.getDecoder().decode(it)
        } ?: generateSalt()

        val spec = PBEKeySpec(
            password.toCharArray(),
            saltBytes,
            usedIterations,
            256
        )

        val factory = SecretKeyFactory.getInstance(usedAlgorithm)

        val hashBytes = factory.generateSecret(spec).encoded

        val hash = Base64.getEncoder().encodeToString(hashBytes)

        val encodedSalt = Base64.getEncoder().encodeToString(saltBytes)

        return PasswordHashResult(
            hash = hash,
            salt = encodedSalt,
            iterations = usedIterations,
            algorithm = usedAlgorithm
        )
    }

    private fun generateSalt(): ByteArray {

        val random = SecureRandom.getInstance(
            CRY.RANDOM_ALGORITHM
        )

        val salt = ByteArray(16)

        random.nextBytes(salt)

        return salt
    }

}
