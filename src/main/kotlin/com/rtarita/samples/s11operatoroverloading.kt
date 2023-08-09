package com.rtarita.samples

// operator overloading allows you to 'hook into' syntactic events in the language
// mostly, those 'events' correspond to specific operators (but not always)

// 1. operator overloads are member (or extension) functions
// They are called when the operator is used on the instance of its class
// You can declare a new operator function in a class with the keywords 'operator fun'
// The name of that function specifies into which syntactic event (operator) you are hooking into
class Bar1 {
    operator fun unaryPlus() { // this is the unary plus operator, e.g. '+bar'
        println("unaryPlus operator was used on a Bar1 instance")
    }
}

// 2. For binary operators, their left argument is 'this' and their right argument is a parameter of the 'operator fun'
class Bar2 {
    operator fun plus(rop: Int) { // this is the binary plus operator, e.g. 'bar1 + bar2'
        println("plus operator was used on a Bar2 instance, right operand is $rop")
    }
}

// 3. Some operators can take a variable number of operands.
// You can either specify an arbitrary number of arguments manually
// Or you can use 'vararg'
class Bar3 {
    operator fun get(arg1: Int, arg2: Int, arg3: Int) { // this is the indexed access operator, e.g. 'bar[1, 2, 3]'
        println("get operator was used on a Bar3 instance, operands are: $arg1, $arg2, $arg3")
    }

    operator fun invoke(vararg args: Int) { // this is the invocation operator, e.g. 'bar(4, 5)'
        println("invoke operator was used on a Bar3 instance, operands are: ${args.joinToString()}")
    }
}

// example of how operator functions don't always have to correspond to a specific operator:
class Bar4 : Iterable<Int> {
    // 'iterator()' is used to iterate over something in a for(each) loop
    // When you override an operator function, you don't have to use the 'operator' keyword, but I included it for clarity
    override operator fun iterator(): Iterator<Int> {
        return listOf(1, 2, 3, 4, 5).iterator()
    }
}

// 'operator fun's might not always be exactly the thing you're looking for
// You can define arbitrary binary 'operators' by using infix functions:
class Bar5 {
    infix fun greet(name: String) {
        println("$this greets $name!")
    }
}

fun main() {
    val bar1 = Bar1()
    bar1.unaryPlus() // operator functions can always be called explicitly
    +bar1 // but of course, you define them to use the syntax construct

    val bar2 = Bar2()
    bar2 + 5 // you can (and should) define a return type for this operator as well

    val bar3 = Bar3()
    bar3[7, 6, 5] // this should also have a return type
    bar3(0, 1, 2, 3, 4, 5, 6, 7, 8, 9) // any number of arguments is fine thanks to 'vararg'

    val bar4 = Bar4()
    // This is where the 'iterator()' operator gets called once
    // Be careful: The 'in' operator normally corresponds to 'operator fun contains(other)' and returns a boolean
    for (i in bar4) {
        print(i)
    }
    println()

    val bar5 = Bar5()
    bar5 greet "Raphael" // 'greet' looks a bit like an operator because it's in infix notation
}

// full list of overloadable operators: https://kotlinlang.org/docs/operator-overloading.html