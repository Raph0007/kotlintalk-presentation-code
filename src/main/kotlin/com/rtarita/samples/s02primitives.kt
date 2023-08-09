package com.rtarita.samples

fun main() {
    val boolean: Boolean = true // true, false
    val char: Char = 'c' // supports unicode
    val byte: Byte = 123 // 8 bit, [-128; 127]
    val short: Short = 12345 // 16 bit, [-32768; 32767]
    val int: Int = 12345678 // 32 bit, [-2147483648; 2147483647]
    val long: Long = 1234567890L // 64 bit, [-9223372036854775808; 9223372036854775807]
    val float: Float = 123.45F // 32-bit floating point
    val double: Double = 123456.789 // 64-bit floating point

    val string: String = "abcdef" // also supports unicode and interpolation

    println(boolean)
    println(char)
    println(byte)
    println(short)
    println(int)
    println(long)
    println(float)
    println(double)
    println(string)
}