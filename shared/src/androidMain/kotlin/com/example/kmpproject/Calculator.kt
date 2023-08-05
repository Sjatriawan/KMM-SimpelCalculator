package com.example.kmpproject

actual class Calculator actual constructor() {
    actual fun add(a: Double, b: Double): Double {
        return a+b
    }

    actual fun multiply(a: Double,b: Double):Double{
        return  a*b
    }
    actual fun subtract(a: Double,b: Double):Double{
        return  a-b
    }

    actual fun divide(a: Double,b: Double):Double{
        return  a/b
    }
}