package com.example.krishiapp.presentation.login

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.krishiapp.R
import com.example.krishiapp.databinding.ActivityLoginBinding
import com.example.krishiapp.databinding.ActivitySignUpBinding
import com.example.krishiapp.domain.User
import com.example.krishiapp.network.ApiService
import com.example.krishiapp.network.RetrofitHelper
import com.example.krishiapp.network.db.DataBaseHandler
import com.example.krishiapp.presentation.dashboard.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        setContentView(binding.root)
        getLogin()
    }
    private fun getLogin() {
        binding.loginBtn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

              val db= DataBaseHandler(this)
            val helper=db.readableDatabase
            val arr= listOf(email,password).toTypedArray()
            val cursor =helper.rawQuery("SELECT * FROM Users WHERE email=? AND password= ? ",arr)
            if (cursor.moveToNext()){
                Toast.makeText(this, "Welcome Successfully login", Toast.LENGTH_SHORT).show()
                val intent=Intent(this,HomeActivity::class.java)
                intent.putExtra("email",email)
                intent.putExtra("password",password)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Invalid credential", Toast.LENGTH_SHORT).show()
            }
        /*  val call=RetrofitHelper.getRetroInstance().create(ApiService::class.java)
                .setLoginDetail(email, password)
            call.enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {

                    }
                    Toast.makeText(this@LoginActivity, "succesfull", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

            })*/
        }
    }
}