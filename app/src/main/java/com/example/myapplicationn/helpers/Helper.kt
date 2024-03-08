package com.example.myapplicationn.helpers


fun extractNumericPart(input: String): Int {
    val regex = Regex("\\d+")
    val matchResult = regex.find(input)
    return matchResult?.value?.toIntOrNull() ?: 0
}

