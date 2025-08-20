package com.example.licznikpunktow

data class Session(
    val id: Int,
    val date: String,
    val competition: String,
    val points: Int
) {
    override fun toString(): String {
        return "$date | $competition | $points pkt"
    }
}
