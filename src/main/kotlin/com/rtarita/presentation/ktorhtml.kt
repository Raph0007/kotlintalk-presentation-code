package com.rtarita.presentation

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.br
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.li
import kotlinx.html.meta
import kotlinx.html.p
import kotlinx.html.title
import kotlinx.html.ul

fun main() {
    embeddedServer(
        factory = CIO,
        port = 8080,
        module = Application::module4
    ).start(wait = true)
}

data class Store(val thingsILike: List<String>)

fun Application.module4() {
    val store = Store(listOf("A", "B", "C"))
    routing {
        get("/hello/{name}") {
            call.respondHtml {
                body {
                    ul {
                        for (thing in store.thingsILike) {
                            li { +thing }
                        }
                    }
                }
            }
        }
    }
}

fun HTML.renderIndex() {
    head {
        title("My Website")
        meta("charset", "utf-8")
    }
    body {
        h1 { +"This is my Website!" }
        p {
            +"Templating with Ktor and kotlinx-html is easy"
            br
            +"It works by utilizing a builder DSL"
            br
            +"which resembles HTML"
        }
        ul {
            li { +"Item 1" }
            li { +"Item 2" }
            li { +"Item 3" }
        }
    }
}
