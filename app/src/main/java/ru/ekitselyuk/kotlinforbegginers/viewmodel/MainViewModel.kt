package ru.ekitselyuk.kotlinforbegginers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ekitselyuk.kotlinforbegginers.model.Repository
import ru.ekitselyuk.kotlinforbegginers.model.RepositoryImpl

class MainViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repo: Repository = RepositoryImpl

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getWeatherFromLocalStorageRus() = getDataFormLocalSource(true)

    fun getWeatherFromLocalStorageWorld() = getDataFormLocalSource(false)

    fun getWeatherFromRemoteSource() = getDataFormLocalSource(true)

    private fun getDataFormLocalSource(isRussian: Boolean = true) {

        liveDataToObserve.value = AppState.Loading

        Thread {

            Thread.sleep(1000)

            val weather = if (isRussian) {
                repo.getWeatherFromLocalStorageRus()
            } else {
                repo.getWeatherFromLocalStorageWorld()
            }

            liveDataToObserve.postValue(AppState.Success(weather))

        }.start()
    }
}