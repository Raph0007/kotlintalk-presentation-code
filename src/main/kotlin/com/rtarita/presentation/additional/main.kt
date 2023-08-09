package com.rtarita.presentation.additional

import com.rtarita.presentation.StringUtil
import com.rtarita.presentation.charFrequency

fun main() {
    val testString = "Hello, World!"
    println(testString.charFrequency('l'))
    println(StringUtil.charFrequency(testString, 'l'))
}