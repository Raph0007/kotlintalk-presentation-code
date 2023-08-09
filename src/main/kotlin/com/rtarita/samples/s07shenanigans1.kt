package com.rtarita.samples

// 1. 'Nothing' can be used for throwing functions:
fun throwsError1(): Nothing {
    throw AssertionError("error")
}

// 2. But actually, 'Nothing' is the type of the 'throw' expression:
fun throwsError2(): Nothing {
    return throw AssertionError("error")
}

// 3. Or, as an expression function
fun throwsError3(): Nothing = throw AssertionError("error")

fun main() {
    // 4. technically, you can store the 'result' of a 'Nothing' function in a variable
    // but in practice, 'Nothing' tells you that the code will terminate before producing any result
    // the compiler still accepts it
    val resultThatNeverExists1 = throwsError1() // or any other throwsError()

    // 5. remember the 'bottom type' thing? 'Nothing' is subtype of every other type.
    // Therefore, according to Liskov, it can be SUBSTITUTED for any other type

    val resultThatNeverExists2: Int = throwsError2()
    val resultThatNeverExists3: String = throwsError3() // works with every predefined or custom type

    // 6. It doesn't need to be a 'Nothing' function, it can also be an expression
    val resultThatNeverExists4: Double = throw AssertionError("error")

    // 7. There are certain keywords that count as expressions of type 'Nothing' in Kotlin:
    val throwExpression = throw AssertionError("error")
    val returnExpression = return
    while (true) {
        val breakExpression = break
        val continueExpression = continue
    }

    // 8. expressions like 'throw' or 'return' can take other expressions after them
    // for example, 'throw' takes an expression of type 'Throwable' after it
    // but 'Nothing' is a subtype of 'Throwable', soooo...
    // https://betterprogramming.pub/short-circuits-bottom-types-and-the-vacuous-boomerang-2114bca82b3f
    throw throw throw return
    throw return return
    throw return return return
    throw return return
}

// 9. Putting it all together: This function has the return type of 'Char' but will never return
private fun shenanigans(): Char {
    while (true) {
        takeString(return throw continue)
        takeString(throw return break)
    }
}

private fun takeString(string: String) {
    println(string)
}