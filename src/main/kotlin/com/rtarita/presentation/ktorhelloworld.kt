package com.rtarita.presentation

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(
        factory = CIO,
        port = 8080,
        module = Application::module1
    ).start(wait = true)
}

fun Application.module1() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
    }
}
