package com.example.writeups

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
//import codepurviewbinding.databinding.ActivityMainBinding

import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        val titles = ArrayList<String>()
        val db = WriteDBHelper(this)
        val rvAdapter = RecyclerViewAdapter(this, this, ids, writeups, authors, titles)
        getData(db, ids, writeups, authors,titles)


//    LinearLayoutManager(this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = rvAdapter


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1){
            recreate()
        }
    }
    fun getData(db:WriteDBHelper, ids:ArrayList<Int>, writeups:ArrayList<String>, authors:ArrayList<String>,titles:ArrayList<String>){
        val cursor = db.readData()
        if (cursor== null){

        } else {
            while (cursor.moveToNext()){
                ids.add(cursor.getInt(0))
                titles.add(cursor.getString(1))
                writeups.add(cursor.getString(2))
                authors.add(cursor.getString(3))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAll){
            Log.d("click","delete")
            val db = WriteDBHelper(this)
            db.deleteAllData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
//            recreate()
        }
        return super.onOptionsItemSelected(item)
    }
    fun delete(id:String){
        val db = WriteDBHelper(this)
        db.deleteOneItem(id)
    }
    private fun confirmDialog(id:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete ${title}?")
        builder.setMessage("Are you sure you want to delete ${title}?")
        builder.setPositiveButton("Yes") { _, _ ->
            // User clicked Yes, so delete the course
            delete(id)
            Toast.makeText(this, "$id deleted", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No"){ _, _->

        }
        builder.create().show()
//        builder.setPositiveButton("Yes", DialogInterface.OnClickListener()
    }

}
