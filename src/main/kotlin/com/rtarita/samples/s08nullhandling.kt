package com.rtarita.samples

import kotlin.random.Random

fun main() {
    safeCall()
    doubleBang()
    elvis()
    smartCast()
}

private fun safeCall() {
    val maybeNull = if (Random.nextBoolean()) "hello, world!" else null

    // safe call operator: Execute right operand member expression if left operand is not null, return null otherwise
    val mayAlsoBeNull = maybeNull?.uppercase()

    println(maybeNull)
    println(mayAlsoBeNull)
}

private fun doubleBang() {
    val maybeNull = if (Random.nextBoolean()) 'c' else null

    // non-null-assert operator (double bang): take value if not null, throw NPE if null
    // ONLY USE IF YOU CAN BE CERTAIN THE VALUE IS NOT NULL AT THIS POINT (and the compiler isn't, for some reason)
    val notNull = maybeNull!! // you can also chain member expressions after this

    println(maybeNull)
    println(notNull)
}

private fun elvis() {
    val maybeNull = if (Random.nextBoolean()) 42 else null

    // elvis operator: Take left operand if not null, right operand otherwise
    val notNull = maybeNull ?: 5

    println(maybeNull)
    println(notNull)

    // this is also where the bottom type shenanigans from the last sample can come in very handy
    // the type of the entire elvis operator expression is the nearest common ancestor between left (non-nullified) and right operand
    // left operand type is 'Int?' -> non-nullified: 'Int'
    // right operand type is 'Nothing'
    // because 'Nothing' is a subtype of 'Int', the NCA is 'Int'
    val notNullOrReturn = maybeNull ?: return
    println(notNullOrReturn)
}

private fun smartCast() {
    val maybeNull: Double? = if (Random.nextBoolean()) 12.34 else null // explicit type for clarity

    if (maybeNull != null) {
        // here, 'maybeNull' is smart-casted from 'Double?' (supertype) to 'Double' (subtype)
        // smart casts not only work for nullability but for supertype/subtype relations in general
        // compiler can also infer way more complex smart casts than this example
        println(maybeNull + 3.14)
    }

    println(maybeNull)
}