package com.rtarita.presentation

fun findSum1(numbers: List<Int>): Int {
    var sum = 0
    for (it in numbers) {
        if (it % 2 == 0) {
            sum += it * it
        }
    }
    return sum
}

fun findSum2(numbers: List<Int>): Int {
    return numbers.filter { it % 2 == 0 }
        .map { it * it }
        .sum()
}

fun findSum3(numbers: List<Int>): Int {
    return numbers.filter { it % 2 == 0 }
        .sumOf { it * it }
}

fun findSum(numbers: List<Int>) = numbers.filter { it % 2 == 0 }.sumOf { it * it }