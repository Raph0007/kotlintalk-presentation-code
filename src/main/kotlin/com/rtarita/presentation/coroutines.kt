package com.rtarita.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

suspend fun readFile(): String {
    delay(5000)
    return "file contents"
}

fun main() {
    val scope = CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    val future = scope.async { readFile() }
    val result = scope.async {
        val res = future.await()
        println("file contents read")
        res.uppercase()
    }
    println("reading file...")
    runBlocking { println(result.await()) }
}