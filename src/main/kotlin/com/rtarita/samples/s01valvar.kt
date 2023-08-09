package com.rtarita.samples

fun main() {
    val integerValue: Int = 5
    val inferred = 5

    var reassignable = 5

    // integerValue = 6 // compilation error

    reassignable = 6 // ok

    println(integerValue)
    println(inferred)
    println(reassignable)
}