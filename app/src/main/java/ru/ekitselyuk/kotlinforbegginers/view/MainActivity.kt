package ru.ekitselyuk.kotlinforbegginers.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ekitselyuk.kotlinforbegginers.R
import ru.ekitselyuk.kotlinforbegginers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment.newInstance())
            .commit()
    }
}
