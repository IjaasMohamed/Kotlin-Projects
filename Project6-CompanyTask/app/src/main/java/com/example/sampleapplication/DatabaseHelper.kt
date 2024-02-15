package com.example.sampleapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val queryUser = "CREATE TABLE $TABLE_USER (" +
                "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_user TEXT, " +
                "$COL_USER_EMAIL TEXT, " +
                "$COL_USER_PASSWORD TEXT)"
        db?.execSQL(queryUser)

        val queryEmployee = "CREATE TABLE $TABLE_EMPLOYEE (" +
                "$COL_EMPLOYEE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_EMPLOYEE_NAME TEXT, " +
                "$COL_EMPLOYEE_EMAIL TEXT, " +
                "$COL_EMPLOYEE_ADDRESS TEXT, " +
                "$COL_EMPLOYEE_PHONE TEXT)"
        db?.execSQL(queryEmployee)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EMPLOYEE")
        onCreate(db)
    }

    fun registerUser(user: User){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COL_user, user.username)
        value.put(COL_USER_EMAIL, user.email)
        value.put(COL_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, value)
        db.close()
    }
    fun registerEmployee(employee: Employee){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COL_EMPLOYEE_NAME, employee.name)
        value.put(COL_EMPLOYEE_EMAIL, employee.email)
        value.put(COL_EMPLOYEE_ADDRESS, employee.address)
        value.put(COL_EMPLOYEE_PHONE, employee.phone)

        db.insert(TABLE_EMPLOYEE, null,value)
        db.close()
    }
    fun loginUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(COL_USER_ID)
        val selection = "$COL_USER_EMAIL = ? AND $COL_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount = cursor.count
        cursor.close()
        db.close()

        return cursorCount > 0
    }

    @SuppressLint("Range")
    fun getAllEmployees(): List<Employee> {
        val employeeList = mutableListOf<Employee>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_EMPLOYEE"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COL_EMPLOYEE_ID))
                val name = cursor.getString(cursor.getColumnIndex(COL_EMPLOYEE_NAME))
                val email = cursor.getString(cursor.getColumnIndex(COL_EMPLOYEE_EMAIL))
                val address = cursor.getString(cursor.getColumnIndex(COL_EMPLOYEE_ADDRESS))
                val phone = cursor.getString(cursor.getColumnIndex(COL_EMPLOYEE_PHONE))

                val employee = Employee(id, name, email, address, phone)
                employeeList.add(employee)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return employeeList
    }
    fun updateEmployee(employee: Employee) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_EMPLOYEE_NAME, employee.name)
            put(COL_EMPLOYEE_EMAIL, employee.email)
            put(COL_EMPLOYEE_ADDRESS, employee.address)
            put(COL_EMPLOYEE_PHONE, employee.phone)
        }
        val selection = "$COL_EMPLOYEE_ID = ?"
        val selectionArgs = arrayOf(employee.id.toString())
        db.update(TABLE_EMPLOYEE, values, selection, selectionArgs)
        db.close()
    }

    fun deleteEmployee(employeeId: Int) {
        val db = this.writableDatabase
        val selection = "$COL_EMPLOYEE_ID = ?"
        val selectionArgs = arrayOf(employeeId.toString())
        db.delete(TABLE_EMPLOYEE, selection, selectionArgs)
        db.close()
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TABLE_USER = "tbl_user"
        private const val TABLE_EMPLOYEE = "tbl_employee"
        private const val COL_USER_ID = "user_id"
        private const val COL_user= "user_name"
        private const val COL_USER_EMAIL= "user_email"
        private const val COL_USER_PASSWORD="user_password"
        private const val COL_EMPLOYEE_ID = "employee_id"
        private const val COL_EMPLOYEE_NAME = "employee_name"
        private const val COL_EMPLOYEE_EMAIL = "employee_email"
        private const val COL_EMPLOYEE_ADDRESS = "employee_address"
        private const val COL_EMPLOYEE_PHONE = "employee_phone"
    }
}
