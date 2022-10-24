package ru.ekitselyuk.kotlinforbegginers.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ekitselyuk.kotlinforbegginers.R
import ru.ekitselyuk.kotlinforbegginers.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val items = listOf("Ābols", "Zēns", "Kīno", "šķerle", "kāķis")
            .map { Item(it) }


        val adapter = IgnoreAccentsArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            items.toMutableList()
        )
        binding.actv.setAdapter(adapter)
        binding.actv.threshold = 0
        binding.actv.setOnTouchListener { _, _ ->
            binding.actv.showDropDown()
            binding.actv.requestFocus()
            false
        }
    }

}

data class Item(val displayName: String) {
    override fun toString(): String {
        return displayName
    }
}