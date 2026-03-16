package com.example.iteratine

data class Routine (
    val id: Int,
    val name: String,
    val startTimestamp: Long,
    val days: List<Day>
)

enum class Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}



