package com.rtarita.presentation

import io.ktor.server.application.Application
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(
        factory = CIO,
        port = 8080,
        module = Application::module2
    ).start(wait = true)
}

fun Application.module2() {
    routing {
        route("/myroute") {
            get {
                // ...
            }
            post {
                // ...
            }
            delete {
                // ...
            }
        }
    }
}
