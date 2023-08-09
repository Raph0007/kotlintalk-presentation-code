package com.rtarita.samples

// 1. declared with the keyword 'class'. Default visibilty is 'public' for class itself and all members
class Foo1

// 2. members are placed inside braces
class Foo2 {
    private val memberProperty = 5

    fun bar() {
        println("Foo2#bar() called, member property is $memberProperty")
    }
}

// 3. Primary constructor: Member properties declaration and constructor parameter declaration in one
class Foo3(private val memberProperty: Int) {
    fun bar() {
        println("Foo3#bar() called, member property is $memberProperty")
    }
}

// 3.1. if you need modifiers (visibility, annotations) applying to the primary constructor, you can mention it explicitly.
// in this case, the class is public (by default), but its primary constructor is private
class Foo3x1 private constructor(private val memberProperty: Int) {
    fun bar() {
        println("Foo3x1#bar() called, member property is $memberProperty")
    }
}

// 4. init block: if you want to execute code in the "primary constructor" like you would in constructors in other languages,
// you can use an 'init' block.
// you can also initialize non-constructor member properties without the 'init' block
class Foo4(private val memberProperty: Int) {
    private val nonConstructorProperty = memberProperty * 10 // this kind of behaviour does not need 'init'

    init {
        // this kind of behavior needs 'init'
        println("initialized Foo4, member property is $memberProperty, non-constructor member property is $nonConstructorProperty")
    }

    fun bar() {
        println("Foo4#bar() called, member property is $memberProperty, non-constructor member property is $nonConstructorProperty")
    }
}

// 4.1. The primary constructor can have arguments that are not properties. They only exist for the scope of initializing other properties
// and for usage in the 'init' block
class Foo4x1(constructorArg: Int) {
    private val nonConstructorProperty = constructorArg * 10 // primary constructor argument is available here

    init {
        // primary constructor argument is available here
        println("initialized Foo4x1, constructor arg is $constructorArg, non-constructor member property is $nonConstructorProperty")
    }

    fun bar() {
        // println(constructorArg) // compiler error, constructorArg is only available during initialization
        println("Foo4x1#bar() called, non-constructor member property is $nonConstructorProperty")
    }
}

// 5. secondary constructors: In order to have different constructor overloads, you can declare any number of secondary constructors
// BUT: every secondary constructor HAS to call the primary constructor before any code is executed
// You can also let a secondary constructor call another secondary, as long as it's not cyclical and eventually ends up at the primary
// Also, secondary constructors cannot declare properties, they only have regular arguments
class Foo5(private val memberProperty: Int) {
    init {
        println("init is the 'body' of the primary, therefore gets executed before the body of the secondary")
    }

    constructor(a: Int, b: Int) : this(a + b) { // this() calls the primary constructor
        println("secondary constructor was called with $a and $b")
    }

    fun bar() {
        println("Foo5#bar() called, member property is $memberProperty")
    }
}

// 6. 'object's: Objects in Kotlin are just Singletons. They are instantiated upon classloading, and live for the entire span of the application
// Generally speaking, only one instance of each 'object' can ever exist, but reflection can circumvent this (don't try at home)
// 'object's don't have constructors, but can have an 'init' block (rarely used)
object Foo6 {
    val memberProperty = 5

    init {
        println("this is executed as soon as Foo6 is classloaded")
    }

    fun bar() {
        println("Foo6#bar() called, member property is $memberProperty")
    }
}

// 7. Nesting: Classes in Kotlin can arbitrarily nest other things in them:
// - classes (won't cover)
// - 'inner' classes (won't cover)
// - enums, interfaces, annotations, ... (won't cover)
// - objects (as shown below)
class Foo7(private val memberProperty: Int) {
    object Nested {
        // nested always means static, so we cannot access per-instance members of 'Foo7'
        // the only exception are inner classes, which are equivalent to non-statically nested classes in Java
        val staticProperty = 5
    }

    fun bar() {
        println("Foo7#bar() called, member property is $memberProperty, property of nested object is ${Nested.staticProperty}")
    }
}

// 8. 'companion object's: Nesting objects inside classes enables something equivalent to static members, WHICH KOTLIN DOES NOT HAVE.
// The only issue is that we always have to fully qualify the 'static' member by the name of the nested object.
// You can prevent this with a 'companion object': In every way same as a regular nested object, but when accessing its members, you can omit its name
// 'companion object's can only exist when nested, and there can at most be one companion object per class
class Foo8(private val memberProperty: Int) {
    companion object Nested { // works just like regular 'object'
        val staticProperty = 5
    }

    fun bar() {
        // note how we don't have to write 'Nested.staticProperty'
        println("Foo8#bar() called, member property is $memberProperty, property of companion object is $staticProperty")
    }
}

// 8.1. Because there can only be at most one companion object per class, you don't have to give it a name
// When no name is specified at declaration, it will get a default name, 'Companion'. This name is rarely referenced.
class Foo8x1(private val memberProperty: Int) {
    companion object { // no name given, it will be called 'Companion'
        val staticProperty = 5
    }

    fun bar() {
        println("Foo8x1bar() called, member property is $memberProperty, property of companion object is $staticProperty")
    }
}

fun main() {
    Foo6 // classload Foo6, trigger 'init' right at the beginning
    println()

    val foo1 = Foo1() // there is no 'new' (like in Java)
    println(foo1)
    println()

    val foo2 = Foo2()
    // println(foo2.memberProperty) // compiler error: memberProperty is private in Foo2
    foo2.bar()
    println()

    val foo3 = Foo3(42) // primary constructor invocation: nothing special at call-site
    foo3.bar()
    // val foo3x1 = Foo3x1(42) // compiler error: primary constructor is private
    println()

    val foo4 = Foo4(42)
    foo4.bar()
    val foo4x1 = Foo4x1(42)
    foo4x1.bar()
    println()

    var foo5 = Foo5(42) // we can call the primary constructor
    foo5 = Foo5(23, 19) // or we can call the secondary constructor
    foo5.bar()
    println()

    Foo6.bar() // we don't have to call any constructor, the instance already exists
    println()

    val foo7 = Foo7(42)
    println(Foo7.Nested.staticProperty) // access of 'static' property needs to be qualified by the name of the nested object
    foo7.bar()
    println()

    val foo8 = Foo8(42)
    println(Foo8.staticProperty) // access of 'static' property does not need to be qualified thanks to companion object, but...
    println(Foo8.Nested.staticProperty) // it CAN be qualified
    foo8.bar()
    println()

    val foo8x1 = Foo8x1(42)
    println(Foo8x1.Companion.staticProperty) // if you want to qualify a member of an unnamed companion object, use 'Companion'
    foo8x1.bar()
    println()
}