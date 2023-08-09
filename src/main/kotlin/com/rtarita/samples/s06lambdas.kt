package com.rtarita.samples

private fun takeSimpleRunnable(runnable: () -> Unit) {
    runnable()
}

private fun takeOneParam(lambda: (Int) -> Unit) {
    lambda(42)
}

private fun takeTwoParams(lambda: (Int, String) -> Unit) {
    lambda(42, "test")
}

private fun takeDestructurableParam(lambda: (Pair<Int, String>) -> Unit) {
    lambda(42 to "test")
}

private fun takeReceiver(lambda: String.() -> Unit) {
    "test".lambda()
}

private fun everything(lambda: String.(Int, Pair<String, Boolean>) -> Double) {
    val result = "test1".lambda(42, "test2" to true)
    println(result)
}

fun main() {
    takeSimpleRunnable({ println("'regular' syntax") })
    takeSimpleRunnable() { println("trailing lambda syntax (preferred)") }
    takeSimpleRunnable { println("parentheses can be omitted when there's no other argument than the trailing lambda") }

    // example of HOF in the stdlib
    repeat(5) {
        println("test")
    }

    takeOneParam { param: Int -> // explicit declaration of lambda parameter
        println(param)
    }

    takeOneParam { param -> // with type inference
        println(param)
    }

    takeOneParam {  // special case: If there is only one parameter, it will implicitly be available as 'it'
        println(it)
    }

    takeTwoParams { intArg, stringArg -> // no implicit parameters if there are 2 or more
        println(intArg)
        println(stringArg)
    }

    takeDestructurableParam { pair -> // can also be omitted for implicit 'it'
        println(pair.first)
        println(pair.second)
    }

    takeDestructurableParam { (intArg, stringArg) -> // parentheses: destructuring
        println(intArg)
        println(stringArg)
    }

    takeReceiver {// there is no parameter, only a receiver
        println(uppercase()) // receiver becomes 'this' in the current scope, members can be accessed directly
    }

    everything { intArg, (stringArg, booleanArg) ->
        println("receiver: $this")
        println("intArg: $intArg")
        println("stringArg: $stringArg")
        println("booleanArg: $booleanArg")
        42.0 // lambdas (can) return implicitly, 'return' is not needed
    }
}