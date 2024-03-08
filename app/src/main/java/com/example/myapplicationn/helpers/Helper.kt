package com.example.myapplicationn.helpers

fun extractNumericPart(input: String): Int? {
    val trimmedInput = input.trim()
    var numericPart = ""

    for (char in trimmedInput) {
        if (char.isDigit() && numericPart.length < 2) {
            numericPart += char
        } else if (numericPart.isNotEmpty()) {
            break
        }
    }

    return numericPart.toIntOrNull()
}