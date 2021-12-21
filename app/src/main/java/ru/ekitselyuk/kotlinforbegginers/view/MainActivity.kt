package ru.ekitselyuk.kotlinforbegginers.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ru.ekitselyuk.kotlinforbegginers.R
import ru.ekitselyuk.kotlinforbegginers.databinding.ActivityMainBinding
import ru.ekitselyuk.kotlinforbegginers.model.RepositoryImpl
import ru.ekitselyuk.kotlinforbegginers.model.Weather
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val json = Gson().toJson(RepositoryImpl().getWeatherFromServer())
        Log.d("DEBUGLOG", json )

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment.newInstance())
            .commit()
    }
}
