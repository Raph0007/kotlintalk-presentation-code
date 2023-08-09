package com.rtarita.presentation

import kotlin.reflect.KProperty

class Delegatable<T>(private var internalValue: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        println("delegated property was accessed")
        return internalValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        println("delegated property was assigned")
        internalValue = value
    }
}

fun main() {
    var foo by Delegatable(45)
    val bar = foo // access -> getValue
    foo = 7 // reassignment -> setValue
}