package ru.ekitselyuk.contentproviderclient

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

private val HISTORY_URI: Uri = Uri.parse("content://geekbrains.provider/HistoryEntity")

class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.text_view).setOnClickListener {
            val contentResolver: ContentResolver = contentResolver
            val cursor: Cursor? = contentResolver.query(HISTORY_URI, null, null, null, null)

            val list = mutableListOf<String>()

            cursor?.let {
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val id = cursor.getLong(cursor.getColumnIndex("id"))
                        val city = cursor.getString(cursor.getColumnIndex("city"))
                        val temperature = cursor.getInt(cursor.getColumnIndex("temperature"))

                        list.add("$id - $city - $temperature")
                    }
                }

                AlertDialog.Builder(this)
                    .setItems(list.toTypedArray()) { _, _ -> }
                    .setPositiveButton("OK") { _, _ -> }
                    .create()
                    .show()

                cursor.close()
            }
        }
    }
}