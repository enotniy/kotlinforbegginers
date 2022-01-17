package ru.ekitselyuk.kotlinforbegginers.view

import android.Manifest
import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import ru.ekitselyuk.kotlinforbegginers.R

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contants)

        checkPermission()
    }

    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            when {
                result -> getContacts()
                !shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) -> AlertDialog.Builder(this).setTitle("Дай доступ")
                    .setMessage("Ну очень надо")
                    .setPositiveButton("Дать доступ") { _, _ -> checkPermission() }
                    .setNegativeButton("Не давать доступ") { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()
                else -> Toast.makeText(this, "T_T", Toast.LENGTH_LONG).show()
            }
        }

    private fun checkPermission() {
        permissionResult.launch(Manifest.permission.READ_CONTACTS)
    }

    private fun getContacts() {
        val contentResolver: ContentResolver = contentResolver
        val contactsList = findViewById<TextView>(R.id.contacts_list).apply {
            text = ""
        }

        val cursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        )

        cursor?.let {
            val columnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            if (columnIndex >= 0) {
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name = cursor.getString(columnIndex)
                        contactsList.text = "${contactsList.text}$name\n"
                    }
                }
            }
            cursor.close()
        }

    }
}