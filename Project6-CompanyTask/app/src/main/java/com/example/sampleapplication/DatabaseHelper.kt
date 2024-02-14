package com.example.sampleapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_USER (" +
                "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_USER_NAME TEXT, " +
                "$COL_USER_EMAIL TEXT, " +
                "$COL_USER_PASSWORD TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    fun registerUser(user: User){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COL_USER_NAME, user.username)
        value.put(COL_USER_EMAIL, user.email)
        value.put(COL_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, value)
        db.close()
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TABLE_USER = "tbl_user"
        private const val COL_USER_ID = "user_id"
        private const val COL_USER_NAME= "user_name"
        private const val COL_USER_EMAIL= "user_email"
        private const val COL_USER_PASSWORD="user_password"
    }
}