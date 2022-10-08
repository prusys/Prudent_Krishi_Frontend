package com.example.krishiapp.network.db

import android.content.Context
import android.util.Log

class SessionManager(val context: Context) {

    private val TAG=SessionManager::class.java.toString()
    val PRIVATE_MODE:Int=0

    private val PREF_NAME="AndroidHiveLogin"
    private val KEY_IS_LOGGED_IN="isLoggedIn"
    private val PASSWORD="password"
    val pref=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        val editor=pref.edit()

    fun setLogin(isLoggedIn: Boolean) {
        editor.putBoolean(KEY_IS_LOGGED_IN,isLoggedIn)
      editor.commit()
        Log.d(TAG,"User Login session modified")
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_IS_LOGGED_IN,false)
    }
}