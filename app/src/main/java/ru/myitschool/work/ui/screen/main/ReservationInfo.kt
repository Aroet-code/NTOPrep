package ru.myitschool.work.ui.screen.main

import java.time.LocalDateTime

data class ReservationInfo (
    val dateTime: LocalDateTime,
    val place: String
) {
    fun isEarlier(other: ReservationInfo): Boolean
    {
        return dateTime.isBefore(other.dateTime)
    }
}