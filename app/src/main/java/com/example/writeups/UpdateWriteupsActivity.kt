package com.example.writeups

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.nio.file.Files.delete

class UpdateWriteupsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_writeups)

        val write_upUpdate:EditText = findViewById(R.id.updateWriteUp)
        val authorUpdate:EditText = findViewById(R.id.updateAuthorName)
        val titleUpdate:EditText = findViewById(R.id.updateTitle)
        val buttonUpdate:ImageButton = findViewById(R.id.updateButton)
        val buttonDelete:ImageButton = findViewById(R.id.deleteButton)
        showExistingData(write_upUpdate, authorUpdate,titleUpdate)
        val id = intent?.getStringExtra("id").toString()
        val db = WriteDBHelper(this)
        buttonUpdate.setOnClickListener {
            db.updateData(id, authorUpdate?.text.toString(), write_upUpdate?.text.toString(), titleUpdate?.text.toString())
            finish()
        }
        buttonDelete.setOnClickListener {
            confirmDialog(id)
//            finish()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
    }
    private fun showExistingData(write_upUpdate:EditText, authorUpdate:EditText,titleUpdate:EditText){
        if (intent.hasExtra("author")&&intent.hasExtra("writeup")&&intent.hasExtra("title")){
            val title = intent.getStringExtra("title")
            val writeup = intent.getStringExtra("writeup")
            val author = intent.getStringExtra("author")
            write_upUpdate.setText(writeup)
            authorUpdate.setText(author)
            titleUpdate.setText(title)

        } else {

        }
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
            finish()
        }

        builder.setNegativeButton("No"){ _, _->

        }
        builder.create().show()
//        builder.setPositiveButton("Yes", DialogInterface.OnClickListener()
    }
}