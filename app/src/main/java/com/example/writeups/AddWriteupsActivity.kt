package com.example.writeups

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.writeups.databinding.ActivityMainBinding

class AddWriteupsActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_add_writeups)

        val write_up = findViewById<EditText>(R.id.writeUp)
        val author = findViewById<EditText>(R.id.authorName)
        val button = findViewById<Button>(R.id.button)

    val writeDBHelper = WriteDBHelper(this)

    button.setOnClickListener {
        writeDBHelper.addData(author.text.toString().trim(), write_up.text.toString().trim())
    }

    }
}