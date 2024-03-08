package com.example.myapplicationn.helpers

fun extractNumericPart(input: String): Int? {
    val regex = Regex("\\b(\\d+)\\b")
    val matchResult = regex.find(input)
    return matchResult?.groupValues?.get(1)?.toIntOrNull()
}
