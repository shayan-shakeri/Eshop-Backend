package com.shayan.feature.support_message.websocket

import io.ktor.server.websocket.*
import java.util.concurrent.ConcurrentHashMap

object SupportSessionManager {

    private val sessions =
        ConcurrentHashMap<
                String,
                MutableSet<DefaultWebSocketServerSession>
                >()

    fun connect(
        chatId: String,
        session: DefaultWebSocketServerSession
    ) {
        sessions
            .getOrPut(chatId) {
                mutableSetOf()
            }
            .add(session)
    }

    fun disconnect(
        chatId: String,
        session: DefaultWebSocketServerSession
    ) {
        sessions[chatId]?.remove(session)

        if (sessions[chatId]?.isEmpty() == true) {
            sessions.remove(chatId)
        }
    }

    fun getSessions(
        chatId: String
    ): Set<DefaultWebSocketServerSession> =
        sessions[chatId]
            ?: emptySet()
}