package com.rtarita.samples

// now that we have covered classes in-depth and also learned operator overloads, let's look at an actual Kotlin pattern
// Pseudoconstructor Pattern:
// Constructors in Kotlin cannot have additional generic type parameters, but functions can
// Constructors cannot be 'inline', but function can be
// It follows from this that constructors definitely cannot have 'reified' typeargs, which might actually be useful when constructing a new instance

// Solution 1 (not very exciting, but actually used in the stdlib and official libs)
// Name a function exactly like the class (uppercase) and let it return an instance of the class
class Pseudo1(val a: Int, val b: Int) {
    private var c = 0
    fun foo() {
        println("Pseudo1 -> a: $a, b: $b, c: $c")
    }
}

fun Pseudo1(ab: Int): Pseudo1 {
    return Pseudo1(ab / 10, ab % 10)
}

// Problems with this solution:
// 1. We do not have access to private members of 'Pseudo1' ('c')
// 2. Original (primary) constructor is still callable from the outside
// 3. Not very maintainable, what if you rename the class? You always have to remember to refactor this function as well
// 4. Functions in Kotlin should follow camelCase naming, although this case is an exception

// First improvement: Make primary constructor internal
class Pseudo2 internal constructor(val a: Int, val b: Int) {
    private var c = 0
    fun foo() {
        println("Pseudo2 -> a: $a, b: $b, c: $c")
    }
}

fun Pseudo2(ab: Int): Pseudo2 {
    return Pseudo2(ab / 10, ab % 10)
}

// Problems with this solution (additionally):
// 1. The real constructor is 'just' internal, which means it can still be called within this compilation unit
// 2. Ideally, we would want the real constructor to be private, but then it can only be called from within the class
//    So our pseudoconstructor function would not be able to access it

// Second improvement: Oh, I know, let's just put this function in the companion object of the class!
class Pseudo3 private constructor(val a: Int, val b: Int) {
    private var c = 0

    companion object {
        fun Pseudo3(ab: Int): Pseudo3 {
            val res = Pseudo3(ab / 10, ab % 10) // here, we can access the private primary constructor...
            res.c = 5 // ...and the private member property!
            return res
        }
    }

    fun foo() {
        println("Pseudo3 -> a: $a, b: $b, c: $c")
    }
}

// Problems with this solution:
// We solved some of our previous problems:
// - The real constructor is now private
// - The pseudoconstructor can access private members
// But we introduced a new, fatal one:
// Calling our 'pseudoconstructor' now looks like this: Pseudo3.Pseudo3(42)
// That doesn't even remotely mimic a constructor call, which was our initial goal

// Final Solution:
// Remember the 'invoke' operator? It can mimic a 'function call' on an instance
// But what we want is a 'function call' on the class itself (like this: 'Pseudo()') and not on an instance
// Actually, we do have an instance of 'something': The companion object!
// If we define 'operator fun invoke()' on the companion object, we can 'invoke' it: 'Pseudo.Companion()'
// You might remember that the specialty of companion objects is that you can leave out its name when you access a member of it
// Well, if we leave out 'Companion' at callsite, we're left with: 'Pseudo()', which is exactly what we want!
class Pseudo4 private constructor(val a: Int, val b: Int) {
    private var c = 0

    companion object {
        operator fun invoke(ab: Int): Pseudo4 {
            val res = Pseudo4(ab / 10, ab % 10)
            res.c = 5
            return res
        }
    }

    fun foo() {
        println("Pseudo4 -> a: $a, b: $b, c: $c")
    }
}

// Review of this solution:
// - It looks like a constructor call at callsite
// - We have access to private members of 'Pseudo4' inside of 'invoke()'
// - Real primary constructor is not callable from the outside, only the pseudoconstructor is
// - Maintainable: When we rename the class, we don't have to change anything in the pseudoconstructor
// - No weird PascalCase-named function, it is just called 'invoke'
// - We could now make the 'invoke()' function inline and give it reified typeargs, which is why we originally wanted this pattern

fun main() {
    val pseudo11 = Pseudo1(42) // looks like a constructor, but IDE puts it in italic
    val pseudo12 = Pseudo1(4, 2) // 'real' constructor is still callable
    pseudo11.foo()
    pseudo12.foo()

    val pseudo21 = Pseudo2(42)
    val pseudo22 = Pseudo2(4, 2) // 'real' constructor still callable because we are in the same compilation unit (module)
    pseudo21.foo()
    pseudo22.foo()

    val pseudo3 = Pseudo3.Pseudo3(42) // this does not look like a constructor
    // Pseudo3(4, 2) // atleast the primary constructor is now not accessible from the outside
    pseudo3.foo()

    val pseudo41 = Pseudo4(42) // now THIS looks proper!
    val pseudo42 = Pseudo4.Companion(42) // this is what's going on behind the scenes
    // Pseudo4(4, 2) // primary is not accessible
    pseudo41.foo()
    pseudo42.foo()
}