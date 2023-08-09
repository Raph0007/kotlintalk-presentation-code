package com.rtarita.presentation

import com.rtarita.presentation.additional.FooDto
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(
        factory = CIO,
        port = 8080,
        module = Application::module3
    ).start(wait = true)
}

fun Application.module3() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        post("/{id}") {
            println(call.parameters["id"])   // 1234
            println(call.parameters["name"]) // foo
            println(call.receive<FooDto>())  // body object
        }
    }
}
