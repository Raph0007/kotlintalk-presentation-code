package com.rtarita.samples

fun main() {
    voidNoArg()
    voidWithArgs(42, "test")
    println(returnTypeNoArg())
    println(returnTypeWithArgs(42, "test"))
    println(expressionFunction())
    println(42.extensionFunction("test"))
}

fun voidNoArg(): Unit { // explicit return type not necessary
    println("voidNoArg()")
}

fun voidWithArgs(arg1: Int, arg2: String) {
    println("voidWithArgs($arg1, $arg2)")
}

fun returnTypeNoArg(): Int {
    println("returnTypeNoArg(): 42")
    return 42
}

fun returnTypeWithArgs(arg1: Int, arg2: String): Int {
    println("returnTypeWithArgs($arg1, $arg2): 42")
    return 42
}

fun expressionFunction() = 42

fun Int.extensionFunction(arg: String): Int {
    println("${this}.extensionFunction($arg): 42")
    return 42
}