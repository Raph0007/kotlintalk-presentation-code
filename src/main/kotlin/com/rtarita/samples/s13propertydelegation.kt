package com.rtarita.samples

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// let's have a look at another set of operator functions
// Property Delegation is a language feature that allows us to 'proxy' a value with a different value
// We can have an instance of a class floating around and 'pretending' to be an integer, for example
// In practice, this means that the class has to define how to react to two syntactic events:
// 1. The read-access event (getValue): Class is imitating (for example) an integer, so what does it return if it's asked what its value is?
// 2. The write-access event (setValue): Only necessary if you plan to delegate 'var's and not just 'val's

// Simplest possible delegate class: It mimics a value by actually storing a value inside
class Delegate1(private var innerValue: Int) {
    //                                                            vvv
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return innerValue
    }

    //                                                                  vvv
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        innerValue = value
    }
}

// The two marked types have to match up, and they define the type that the delegate is mimicking
// Let's look at the usage of a Delegate:

private fun useDelegate1() {
    // 'by' operator is used to delegate the 'delegatedInt' property to the 'Delegate1' instance
    // WARNING: The operator functions 'getValue' and 'setValue' are NOT overloading 'by'
    // You can overload 'by' with 'operator fun provideDelegate()' but we'll leave this out for now
    var delegatedInt by Delegate1(42)
    println(delegatedInt) // it works like a regular integer, but at this point, 'getValue()' is actually called
    delegatedInt = 5 // here, 'setValue()' is called

    // Because of how we coded 'Delegate1', the 'setValue()' set the innerValue of the delegate
    // Therefore, the next call to 'getValue()' here will return the updated value of 'innerValue', which is 5
    println(delegatedInt)
}

// Let's make things a bit more clear
// In the next example, the delegate prints all interactions, so we can follow along to what's happening

class Delegate2(private var innerValue: Int) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        println("Delegated property '${property.name}' was read inside of instance $thisRef, value is $innerValue")
        return innerValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        println("Delegated property '${property.name} was written to inside of instance $thisRef, old value was $innerValue, new value is $value")
        innerValue = value
    }
}

// For demonstration, usage of 'Delegate2' will happen inside a class:
class Delegate2Test {
    private var someProperty by Delegate2(42)

    fun useDelegate2() {
        println(someProperty)
        someProperty = 5
        println(someProperty)
    }
}

// Here are two very convenient stdlib delegates: Lazy and Observable
// These kinds of patterns are usually a bit clunky to use in other languages which do not have property delegation
// But in Kotlin, using them is as trivial as it gets

private fun useStdlibDelegates() {
    val lazyProp by lazy {
        println("this will only be executed once, when the property is first accessed")
        42
    }
    // let's leave 'lazyProp' sit for a while. It will not initialize until we access its value

    var observableProp: Int by Delegates.observable(42) { prop, old, new ->
        println("Observable property '${prop.name}' was written to, old value is '$old', new value is '$new'")
    }

    println(observableProp) // will just print 42
    observableProp = 5 // will trigger listener
    println(observableProp) // will just print 5

    // now, let's see how 'lazyProp' is doing
    println(lazyProp) // the initializer will be executed here
    println(lazyProp) // property was already initialized, so the initializer will not run again
}

fun main() {
    useDelegate1()
    println()

    Delegate2Test().useDelegate2()
    println()

    useStdlibDelegates()
}