package ru.ekitselyuk.flavorapp

import android.content.Context
import android.widget.Toast

object Buyer {

    fun buy(context: Context) {
        Toast.makeText(context, "это бесплатно!", Toast.LENGTH_LONG).show()
    }
}