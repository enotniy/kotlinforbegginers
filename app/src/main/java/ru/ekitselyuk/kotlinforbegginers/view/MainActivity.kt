package ru.ekitselyuk.kotlinforbegginers.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ekitselyuk.kotlinforbegginers.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainContainer.setOnClickListener { }

        //

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    val name: String = "Egor"
    //val string: String = String.format("Hello, %s", name)
    val string = "Hello, $name"
}
