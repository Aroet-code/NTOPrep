package ru.myitschool.work.data.source

import ru.myitschool.work.data.repo.LoginData
import ru.myitschool.work.data.repo.TestRepository
import ru.myitschool.work.ui.screen.main.ReservationInfo

class TestDataSource {
    private val repository: TestRepository = TestRepository()
    fun checkCode(code: String): Boolean
    {
        return repository.codes.any { it -> it == code }
    }

    fun getReservationsForCode(code: String): List<ReservationInfo>
    {
        repository.loginDatas.forEach { it ->
            if (it.code == code)
                return it.resInfos
        }
        return emptyList()
    }
    fun getLoginDataForCode(code: String): LoginData
    {
        repository.loginDatas.forEach { it ->
            if (it.code == code)
                return it
        }
        return LoginData(code, null, "", mutableListOf<ReservationInfo>())
    }
}
