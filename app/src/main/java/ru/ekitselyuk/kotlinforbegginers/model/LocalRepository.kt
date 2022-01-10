package ru.ekitselyuk.kotlinforbegginers.model

interface LocalRepository {

    fun getAllHistory(): List<Weather>

    fun saveEntity(weather: Weather)

}