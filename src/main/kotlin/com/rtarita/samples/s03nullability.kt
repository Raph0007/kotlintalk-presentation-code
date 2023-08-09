package com.rtarita.samples

fun main() {
    val nonNullable: Int = 5
    val inferredNonNullable = 5

    val nullable: Int? = null
    val inferredNullable = null // !!! Type is 'Nothing?' !!!

    var nonNullableVar: Int = 5
    var inferredNonNullableVar = 5

    nonNullableVar = 6 // OK
    // nonNullableVar = null // compiler error
    inferredNonNullableVar = 6 // OK
    // inferredNonNullableVar = null // compiler error

    var nullableVar: Int? = 5
    var inferredNullableVar = null // !!!

    nullableVar = 6 // OK
    nullableVar = null // OK

    // inferredNullableVar = 6 // compiler error: Type Mismatch 'Int' vs 'Nothing?'

    println(nonNullable)
    println(inferredNonNullable)
    println(nullable)
    println(inferredNullable)
    println(nonNullableVar)
    println(inferredNonNullableVar)
    println(nullableVar)
    println(inferredNullableVar)
}