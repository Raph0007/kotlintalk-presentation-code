package com.rtarita.samples

import kotlin.random.Random

// we will cover classes in-depth in the next sample

enum class Options {
    A,
    B,
    C
}

// for now, just now that 'data' generates 'equals()' for you
data class Foo(val int: Int)

// 'sealed' means that all direct subtypes are known at compile time
sealed interface Base

data class SubInt(val int: Int) : Base
data class SubDouble(val double: Double) : Base
data class SubString(val string: String) : Base

// the 'when' statement (or expression) is the most powerful control structure in Kotlin
// it can do many different things

fun main() {
    val primitiveType = Random.nextInt(1, 6)

    // 1. Works like a regular 'switch' statement
    when (primitiveType) {
        1 -> println("first")
        2 -> println("second")
        3 -> {
            println("multiple statements can be grouped in scope")
            println("third")
        }

        4 -> println("fourth")
        5 -> println("fifth")
        else -> println("other")
    }

    // 2. can be exhaustive (when used as an expression, it has to be exhaustive or have 'else'):
    val enumType = Options.entries.random()
    val result1 = when (enumType) {
        Options.A -> 'a'
        Options.B -> 'b'
        Options.C -> 'c'
    }
    println(result1)

    // 3. can be used for non-primitive or enum types as well:
    val inQuestion = Foo(Random.nextInt(3))
    val zeroFoo = Foo(0)
    val oneFoo = Foo(1)
    val twoFoo = Foo(2)
    when (inQuestion) {
        zeroFoo -> println("was zero")
        oneFoo -> println("was one")
        twoFoo -> println("was two")
        // if not used as an expression, 'else' or exhaustiveness is not necessary (but recommended)
    }

    // 4. 'when' subject can be declared inline:
    when (val subject = Random.nextInt(3)) {
        0 -> println("$subject: zero")
        1 -> println("$subject: one")
        2 -> println("$subject: two")
    }

    // 5. same branch for multiple cases, 'in' operator supported
    when (Random.nextInt(101)) {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> println("single-digit")
        in 10..19 -> println("teens range")
        else -> println("20-100")
    }

    // 6. Kotlin's Alternative to pattern matching:
    // 'when' supports the 'is' operator
    // The 'is' operator supports smart casts
    // Because the NCA is a sealed interface, this when expression is exhaustive
    val subtypes = listOf(SubInt(42), SubDouble(Math.PI), SubString("Hello, World!"))
    val result2 = when (val randomSubtype = subtypes.random()) {
        is SubInt -> "int: ${randomSubtype.int}"
        is SubDouble -> "double: ${randomSubtype.double}"
        is SubString -> "string: ${randomSubtype.string}"
    }
    println(result2)

    // 7. for more complex conditions, 'when' can be 'subject-less'
    // in this case, it behaves exactly like 'if / else if' but is sometimes more concise / elegant
    val randomString = listOf("test1", "test2", "test3").random()
    val result3 = when { // no subject, no parentheses
        Random.nextBoolean() -> "boolean was true"
        Random.nextInt(101) < 20 -> "int was small"
        randomString.contains("2") -> "string was second test"
        else -> "no condition matched"
    }
    println(result3)
}