package ru.myitschool.work.ui

import ru.myitschool.work.ui.screen.main.ReservationInfo
import java.time.LocalDateTime

fun filterByDate(resInfos: List<ReservationInfo>) : List<ReservationInfo>
{
    val result: MutableList<ReservationInfo> = resInfos.toMutableList()
    var i = 0
    var changed = true;
    var temp: ReservationInfo
    while (changed)
    {
        changed = false
        i = 0
        while (i < result.size - 1)
        {
            if (result[i+1].isEarlier(result[i]))
            {
                changed = true
                temp = resInfos[i]
                result[i] = result[i+1]
                result[i+1] = temp
            }
            i++
        }
    }
    return result
}

fun Char.isLatinLetter(): Boolean = this in 'A'..'Z' || this in 'a'..'z'

fun filterDates(resInfos: List<LocalDateTime>) : List<LocalDateTime>
{
    val result: MutableList<LocalDateTime> = resInfos.toMutableList()
    var i = 0
    var changed = true;
    var temp: LocalDateTime
    while (changed)
    {
        changed = false
        i = 0
        while (i < result.size - 1)
        {
            if (result[i+1].isBefore(result[i]))
            {
                changed = true
                temp = resInfos[i]
                result[i] = result[i+1]
                result[i+1] = temp
            }
            i++
        }
    }
    return result
}

fun authStringCriteria(string: String) : Boolean
{
    if (string.length != 4)
        return false
    if (!(string.all { it -> it.isLatinLetter() || it.isDigit() }))
        return false
    return true
}