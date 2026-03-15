package com.example.iteratine

data class Task (
    val id: Int,
    val name: String,
    val duration: Long,
    val emoji: String,
    val note: String,
    val parentRoutineID: Int,
)