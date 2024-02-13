package com.example.sampleapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this, null)

        val enterName = findViewById<EditText>(R.id.enterName)
        val enterAge = findViewById<EditText>(R.id.enterAge)
        val addNameButton = findViewById<Button>(R.id.addName)
        val printNameButton = findViewById<Button>(R.id.printName)
        val nameTextView = findViewById<TextView>(R.id.Name)
        val ageTextView = findViewById<TextView>(R.id.Age)

        addNameButton.setOnClickListener {
            val name = enterName.text.toString()
            val age = enterAge.text.toString()
            dbHelper.addName(name, age)
            enterName.text.clear()
            enterAge.text.clear()
        }

        printNameButton.setOnClickListener {
            val cursor = dbHelper.getName()
            if (cursor?.moveToFirst() == true) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl))
                    val age = cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))
                    nameTextView.append("$name\n")
                    ageTextView.append("$age\n")
                } while (cursor.moveToNext())
            }
            cursor?.close()
        }
    }
}
