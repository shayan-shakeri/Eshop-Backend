package com.shayan.feature.health

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.head
import io.ktor.server.routing.route

fun Route.healthHealthRoute() {
    route("/ping") {
        head {
            call.respond(HttpStatusCode.OK)
        }
    }
}