package ru.myitschool.work.ui.screen.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import ru.myitschool.work.data.source.TestDataSource
import java.time.LocalDateTime

class MainScreenViewModel : ViewModel()
{
    val dataSource: TestDataSource = TestDataSource()
    var authCode: String = ""
    val image = ImageBitmap(200, 200)
    private val _name = MutableStateFlow<String>("Иван Иванович")
    val name: StateFlow<String> = _name.asStateFlow()

    fun setName(other: String)
    {
        _name.value = other
    }

    private val _reservations = mutableStateListOf<ReservationInfo>(
        ReservationInfo(
            LocalDateTime.parse("2026-12-31T23:59:30"), "Место 1",
        ),
        ReservationInfo(
            LocalDateTime.parse("2026-12-31T23:59:30"), "Место 2",
        )
    )
    val reservations = _reservations

    fun setReservations(reservations: MutableList<ReservationInfo>)
    {
        _reservations.clear()
        reservations.forEach {
            _reservations.add(it)
        }
    }

    fun addReservation(reservationInfo: ReservationInfo)
    {
        _reservations.add(reservationInfo)
    }

    fun setCode(other: String)
    {
        authCode = other
    }

    fun updateData()
    {
        val loginData = dataSource.getLoginDataForCode(authCode)
        setReservations(loginData.resInfos)
        setName(loginData.name)
    }
}