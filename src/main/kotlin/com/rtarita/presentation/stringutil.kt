package com.rtarita.presentation

fun String.charFrequency(c: Char): Int {
    return count { it == c }
}