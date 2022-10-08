package com.example.krishiapp.network.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.krishiapp.domain.User

class DataBaseHandler(var context:Context):SQLiteOpenHelper(context,"MYDB",null,1) {

    val TABLE_NAME="Users"
    val USER_ID="id"
    val USER_PHONE="phone"
    val USER_EMAIL="email"
    val USER_PASSWORD="password"
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable="CREATE TABLE $TABLE_NAME ($USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$USER_PHONE TEXT, $USER_EMAIL TEXT , $USER_PASSWORD TEXT)"
        p0?.execSQL(createTable)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


    fun insertData(user: User){
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(USER_PHONE,user.phone)
        cv.put(USER_EMAIL,user.email)
        cv.put(USER_PASSWORD,user.password)
        val result=db.insert(TABLE_NAME,null,cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkUser(user: User){
        val arr= listOf(user.email).toTypedArray()
        val db:SQLiteDatabase=this.writableDatabase

        val cursor:Cursor=db.rawQuery("SELECT * FROM User WHERE email=? ",arr)
    }

    fun readData():MutableList<User>{
        val stdList:MutableList<User> = ArrayList()
        val db=this.readableDatabase
        val query="SELECT * FROM $TABLE_NAME"
        val result=db.rawQuery(query,null)
        if (result.moveToFirst()){
            do {
                val user=User()
                user.id=result.getString(0).toInt()
                user.phone=result.getString(1)
                user.email=result.getString(2)
                user.password=result.getString(3)
                stdList.add(user)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return stdList
    }

    fun updateData(user: User):Int{
        val db=this.writableDatabase
       val contentValues=ContentValues()
        contentValues.put(USER_ID,user.id)
        contentValues.put(USER_PHONE,user.phone)
        contentValues.put(USER_EMAIL,user.email)
        contentValues.put(USER_PASSWORD,user.password)

        return db.update(TABLE_NAME,contentValues,"$USER_ID=?",
            arrayOf(user.id.toString())
        )

    }

}