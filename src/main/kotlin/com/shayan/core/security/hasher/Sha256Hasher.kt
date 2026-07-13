package com.shayan.core.security.hasher

import java.security.MessageDigest

class Sha256Hasher {
    fun hash(hash: String): String {
        val bytes = hash.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

}