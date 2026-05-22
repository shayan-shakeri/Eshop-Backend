package core.util

object EnvLoader {

    fun get(name: String): String {
        return System.getenv(name)
            ?: throw IllegalStateException(name)
    }

    fun getInt(name: String): Int {
        return get(name).toInt()
    }

    fun getLong(name: String): Long {
        return get(name).toLong()
    }

}
