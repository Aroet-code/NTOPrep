package ru.myitschool.work.data.repo

import androidx.compose.ui.graphics.ImageBitmap
import ru.myitschool.work.ui.screen.main.ReservationInfo
import java.time.LocalDateTime

data class LoginData (
    val code: String,
    val imageBitmap: ImageBitmap?,
    val name: String,
    val resInfos: MutableList<ReservationInfo>
)

data class DateData(
    val date: LocalDateTime,
    val places: Map<String, Boolean>
)

data class TestRepository (
    val codes: List<String> = listOf(
        "9999",
        "test"
    ),
    val dates: List<DateData> = listOf(
        DateData(LocalDateTime.parse("2026-12-31T23:59:30"), mapOf(Pair("Место 4", false), Pair("Место 2", false), Pair("Место 3", false))),
        DateData(LocalDateTime.parse("2026-12-30T23:59:30"), mapOf(Pair("Место 1", false)))
    ),
    val loginDatas: List<LoginData> = listOf(
        LoginData(
            "9999", null, "Иван Иванович", mutableListOf(ReservationInfo(LocalDateTime.parse("2026-12-29T23:59:30"), "Секретное место")),
        ),
        LoginData(
            "test", null, "Андрей Андреевич", mutableListOf(ReservationInfo(LocalDateTime.parse("2026-12-28T23:59:30"), "Секретное место")),
        )
    )
)