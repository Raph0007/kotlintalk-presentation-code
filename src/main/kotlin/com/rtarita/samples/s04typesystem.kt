package com.rtarita.samples

fun main() {
    val baseTypeNullable: Any? = "baseTypeNullable"
    val baseType: Any = "baseType"

    val concreteTypeNullable: String? = "concreteTypeNullable"
    val concreteType: String = "concreteType"

    val bottomTypeNullable: Nothing? = null
    val bottomType: Nothing = error("There can never be an instance of 'Nothing'")
}