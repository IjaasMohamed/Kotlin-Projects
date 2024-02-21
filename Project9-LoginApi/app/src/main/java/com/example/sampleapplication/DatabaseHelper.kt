import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sampleapplication.Employee
import com.example.sampleapplication.item.ResponseBody

// import com.example.sampleapplication.User

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
//        val queryUser = "CREATE TABLE $TABLE_USER (" +
//                "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "$COL_user TEXT, " +
//                "$COL_USER_EMAIL TEXT, " +
//                "$COL_USER_PASSWORD TEXT)"
//        db?.execSQL(queryUser)

        val queryEmployee = "CREATE TABLE $TABLE_EMPLOYEE (" +
                "$COL_EMPLOYEE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_EMPLOYEE_NAME TEXT, " +
                "$COL_EMPLOYEE_EMAIL TEXT, " +
                "$COL_EMPLOYEE_ADDRESS TEXT, " +
                "$COL_EMPLOYEE_PHONE TEXT)"
        db?.execSQL(queryEmployee)

        //items
        val createTableStatement = "CREATE TABLE $TABLE_ITEM (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_BARCODE TEXT, " +
                "$COLUMN_CODE TEXT, " +
                "$COLUMN_ITEM_GROUP TEXT, " +
                "$COLUMN_ITEM_GROUP_CODE TEXT, " +
                "$COLUMN_MINUS_STOCK INTEGER, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_ORDER_NO REAL, " +
                "$COLUMN_PACK_SIZE TEXT, " +
                "$COLUMN_PRICE REAL, " +
                "$COLUMN_PRINTING_NAME TEXT, " +
                "$COLUMN_STATUS INTEGER, " +
                "$COLUMN_STOCK_HANDLE INTEGER, " +
                "$COLUMN_SYNC_TIME TEXT)"
        db?.execSQL(createTableStatement)
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_EMPLOYEE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ITEM")

        onCreate(db)
    }
//    fun registerUser(user: User){
//        val db = this.writableDatabase
//        val value = ContentValues()
//        value.put(COL_user, user.username)
//        value.put(COL_USER_EMAIL, user.email)
//        value.put(COL_USER_PASSWORD, user.password)
//
//        db.insert(TABLE_USER, null, value)
//        db.close()
//    }
    fun registerEmployee(employee: Employee) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(COL_EMPLOYEE_NAME, employee.name)
        value.put(COL_EMPLOYEE_EMAIL, employee.email)
        value.put(COL_EMPLOYEE_ADDRESS, employee.address)
        value.put(COL_EMPLOYEE_PHONE, employee.phone)

        db.insert(TABLE_EMPLOYEE, null, value)
//        db.close()
    }
//    fun loginUser(email: String, password: String): Boolean {
//        val db = this.readableDatabase
//        val columns = arrayOf(COL_USER_ID)
//        val selection = "$COL_USER_EMAIL = ? AND $COL_USER_PASSWORD = ?"
//        val selectionArgs = arrayOf(email, password)
//
//        val cursor = db.query(
//            TABLE_USER,
//            columns,
//            selection,
//            selectionArgs,
//            null,
//            null,
//            null
//        )
//        val cursorCount = cursor.count
//        cursor.close()
//        db.close()
//
//        return cursorCount > 0
//    }

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
//        db.close()
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
//        db.close()
    }

    fun deleteEmployee(employeeId: Int) {
        val db = this.writableDatabase
        val selection = "$COL_EMPLOYEE_ID = ?"
        val selectionArgs = arrayOf(employeeId.toString())
        db.delete(TABLE_EMPLOYEE, selection, selectionArgs)
//        db.close()
    }


    //item table
    fun addItem(item: ResponseBody): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_BARCODE, item.Barcode)
        values.put(COLUMN_CODE, item.Code)
        values.put(COLUMN_ITEM_GROUP, item.Item_Group)
        values.put(COLUMN_ITEM_GROUP_CODE, item.Item_Group_Code)
        values.put(COLUMN_MINUS_STOCK, if (item.Minus_Stock) 1 else 0)
        values.put(COLUMN_NAME, item.Name)
        values.put(COLUMN_ORDER_NO, item.Order_No)
        values.put(COLUMN_PACK_SIZE, item.Pack_Size)
        values.put(COLUMN_PRICE, item.Price)
        values.put(COLUMN_PRINTING_NAME, item.Printing_Name)
        values.put(COLUMN_STATUS, item.Status)
        values.put(COLUMN_STOCK_HANDLE, if (item.Stock_Handle) 1 else 0)
        values.put(COLUMN_SYNC_TIME, item.Sync_Time)

        val result = db.insert(TABLE_ITEM, null, values)
//        db.close()

        // If the result is -1, then an error occurred
        return result != -1L
    }
    @SuppressLint("Range")
    fun getAllItems(): List<ResponseBody> {
        val items = ArrayList<ResponseBody>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ITEM", null)

        if (cursor.moveToFirst()) {
            do {
                val barcode = cursor.getString(cursor.getColumnIndex(COLUMN_BARCODE))
                val code = cursor.getString(cursor.getColumnIndex(COLUMN_CODE))
                val itemGroup = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_GROUP))
                val itemGroupCode = cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_GROUP_CODE))
                val minusStock = cursor.getInt(cursor.getColumnIndex(COLUMN_MINUS_STOCK)) == 1
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val orderNo = cursor.getDouble(cursor.getColumnIndex(COLUMN_ORDER_NO))
                val packSize = cursor.getString(cursor.getColumnIndex(COLUMN_PACK_SIZE))
                val price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
                val printingName = cursor.getString(cursor.getColumnIndex(COLUMN_PRINTING_NAME))
                val status = cursor.getInt(cursor.getColumnIndex(COLUMN_STATUS))
                val stockHandle = cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK_HANDLE)) == 1
                val syncTime = cursor.getString(cursor.getColumnIndex(COLUMN_SYNC_TIME))

                val item = ResponseBody(barcode, code, itemGroup, itemGroupCode, minusStock, name, orderNo, packSize, price, printingName, status, stockHandle, syncTime)
                items.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items
    }


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"

        private const val TABLE_EMPLOYEE = "tbl_employee"

        private const val COL_EMPLOYEE_ID = "employee_id"
        private const val COL_EMPLOYEE_NAME = "employee_name"
        private const val COL_EMPLOYEE_EMAIL = "employee_email"
        private const val COL_EMPLOYEE_ADDRESS = "employee_address"
        private const val COL_EMPLOYEE_PHONE = "employee_phone"

        // user table (used when logging in or creating a new user
        //        private const val TABLE_USER = "tbl_user"
        //        private const val COL_USER_ID = "user_id"
        //        private const val COL_user= "user_name"
        //        private const val COL_USER_EMAIL= "user_email"
        //        private const val COL_USER_PASSWORD="user_password"

        //Fetch and store Task 1

        const val TABLE_ITEM = "tbl_items"
        const val COLUMN_ID = "id"
        const val COLUMN_BARCODE = "barcode"
        const val COLUMN_CODE = "code"
        const val COLUMN_ITEM_GROUP = "item_group"
        const val COLUMN_ITEM_GROUP_CODE = "item_group_code"
        const val COLUMN_MINUS_STOCK = "minus_stock"
        const val COLUMN_NAME = "name"
        const val COLUMN_ORDER_NO = "order_no"
        const val COLUMN_PACK_SIZE = "pack_size"
        const val COLUMN_PRICE = "price"
        const val COLUMN_PRINTING_NAME = "printing_name"
        const val COLUMN_STATUS = "status"
        const val COLUMN_STOCK_HANDLE = "stock_handle"
        const val COLUMN_SYNC_TIME = "sync_time"
    }
}
