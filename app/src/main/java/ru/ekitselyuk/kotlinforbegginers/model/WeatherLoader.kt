package ru.ekitselyuk.kotlinforbegginers.model

import android.os.Build
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object WeatherLoader {

    private const val YOUR_API_KEY = "46fe3a8c-5ac7-4650-8578-40ab8b2a1082"

    fun load(city: City, listener: OnWeatherLoadListener) {

        var urlConnection: HttpsURLConnection? = null

        try {
            val uri =
                URL("https://api.weather.yandex.ru/v2/informers?lat=${city.lat}&lon=${city.lon}")

            urlConnection = uri.openConnection() as HttpsURLConnection
            urlConnection.addRequestProperty("X-Yandex-API-Key", YOUR_API_KEY)
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 1000
            urlConnection.connectTimeout = 1000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                reader.lines().collect(Collectors.joining("\n"))
            } else {
                ""
            }

            Log.d("DEBUGLOG", "result: $result")

            val weatherDTO = Gson().fromJson(result, WeatherDTO::class.java)

            listener.onLoaded(weatherDTO)

        } catch (e: Exception) {
            listener.onFailed(e)
            Log.e("DEBUGLOG", "FAIL CONNECTION", e)
        } finally {
            urlConnection?.disconnect()
        }
    }

    interface OnWeatherLoadListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }
}