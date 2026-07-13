package com.shayan.feature.favorites.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.favorites.constants.FavoritesConst
import com.shayan.feature.favorites.dto.AddFavoriteRequest
import com.shayan.feature.favorites.service.FavoritesService
import com.shayan.util.jwt.idExtractor
import core.consts.CJWT
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.favoriteRoute(
    favoriteService: FavoritesService
) {

    route(
        FavoritesConst.MAIN_ROUTE
    ) {

        authenticate(
            CJWT.ACCESS_AUTH
        ) {

            post(
                FavoritesConst.ADD_ROUTE
            ) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<
                            AddFavoriteRequest
                            >()

                call.respond(
                    favoriteService.add(
                        userId,
                        request
                    )
                )
            }

            get(
                FavoritesConst.READ_ROUTE
            ) {

                val userId =
                    call.idExtractor()

                call.respond(
                    favoriteService.read(
                        userId
                    )
                )
            }

            delete(
                FavoritesConst.DELETE_ROUTE
            ) {

                val request =
                    call.receive<
                            IdIpDTO
                            >()

                favoriteService.delete(
                    request
                )

                call.respond(
                    HttpStatusCode.OK
                )
            }
        }
    }
}