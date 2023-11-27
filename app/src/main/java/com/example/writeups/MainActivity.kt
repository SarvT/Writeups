package com.example.writeups

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import codepurviewbinding.databinding.ActivityMainBinding

import android.view.inputmethod.InputBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.writeups.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val btnFloat = findViewById<FloatingActionButton>(R.id.floatingActionButtonMainScreen)
    val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMainScreen)
//        val dbHelper = WriteDBHelper(this)

        btnFloat.setOnClickListener {
            val intent = Intent(this, AddWriteupsActivity::class.java)
            startActivity(intent)
        }



    val writeups = ArrayList<String>()
    val ids = ArrayList<Int>()
    val authors = ArrayList<String>()
    val db = WriteDBHelper(this)
    getData(db, ids, writeups, authors)


    val rvAdapter = RecyclerViewAdapter(this, ids, writeups, authors)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = rvAdapter


    }

    fun getData(db:WriteDBHelper, ids:ArrayList<Int>, writeups:ArrayList<String>, authors:ArrayList<String>){
        val cursor = db.readData()
        if (cursor== null){

        } else {
            while (cursor.moveToNext()){
                ids.add(cursor.getInt(0))
                writeups.add(cursor.getString(1))
                authors.add(cursor.getString(2))
            }
        }
    }
}
