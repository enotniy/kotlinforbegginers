package ru.ekitselyuk.kotlinforbegginers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ekitselyuk.kotlinforbegginers.model.Repository
import ru.ekitselyuk.kotlinforbegginers.model.RepositoryImpl
import ru.ekitselyuk.kotlinforbegginers.model.Weather
import kotlin.random.Random

class DetailViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repo: Repository = RepositoryImpl

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getWeather() {
        liveDataToObserve.value = AppState.Loading

        Thread {

            Thread.sleep(1000)

            if (Random.nextBoolean()) {
                val weather = repo.getWeatherFromServer()
                liveDataToObserve.postValue(AppState.Success(weather))
            } else {
                liveDataToObserve.postValue(AppState.Error(Exception("Проверьте интернет")))
            }

        }.start()
    }
}