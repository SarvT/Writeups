package com.example.writeups

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class WriteDBHelper(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

//    private val context:Context = context
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE ${TABLE_NAME} (${COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${COLUMN_TITLE} TEXT, ${COLUMN_AUTHOR} TEXT);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME}")
        onCreate(db)
    }

    fun addData(author_name:String, writeUp:String){
        val db:SQLiteDatabase = this.writableDatabase
        val cv=ContentValues()
        cv.put(COLUMN_TITLE, writeUp)
        cv.put(COLUMN_AUTHOR, author_name)
        val result = db.insert(TABLE_NAME, null, cv).toString()
        if(result=="-1"){
            Log.d("add operation", "Unsuccessful operation!")
        } else {
            Log.d("add operation", "Successfully added info!")
        }
    }

    fun readData(): Cursor? {
        val query = "SELECT * FROM ${TABLE_NAME}"
        val db = this.readableDatabase
        var cursor:Cursor? = null

        if(db!=null){
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

companion object{

    private final val DATABASE_NAME = "Writeups.db"
    private final val DATABASE_VERSION = 1

    private final val TABLE_NAME = "articles"
    private final val COLUMN_ID = "_id"
    private final val COLUMN_TITLE = "article_title"
    private final val COLUMN_AUTHOR = "article_author"
}
}
