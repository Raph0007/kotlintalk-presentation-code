package com.rtarita.presentation

fun execute1(param: String) {
    println(param.uppercase()) // guaranteed safety
}

fun execute(param: String?) {
    // println(param.uppercase()) // compiler error
    println(param?.uppercase() ?: "default")
}

fun main() {
    execute(null) // ok
    execute("test") // ok
}