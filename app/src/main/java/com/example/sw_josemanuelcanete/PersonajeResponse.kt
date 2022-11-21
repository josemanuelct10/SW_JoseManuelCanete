package com.example.sw_josemanuelcanete

data class PersonajeResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)