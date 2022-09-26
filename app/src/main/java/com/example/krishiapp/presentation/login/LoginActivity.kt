package com.example.krishiapp.presentation.login

import android.content.Intent
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
      //  checkExistence()
        getLogin()
    }

    private fun checkExistence() {
        val sharedPreferences=getSharedPreferences("credential", MODE_PRIVATE)
        if (sharedPreferences.contains("email")){
            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, "Login Again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getLogin() {
        binding.loginBtn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            val call=RetrofitHelper.getRetroInstance().create(ApiService::class.java)
                .setLoginDetail(email, password)
            call.enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                   val obj=response.raw().message().toString()
                    if (obj.equals("OK")){
                        val sharedPreference=getSharedPreferences("credential", MODE_PRIVATE)
                        val editor=sharedPreference.edit()
                        editor.putString("email",binding.edtEmail.text.toString())
                        editor.putString("password",binding.edtPassword.text.toString())
                        editor.commit()
                        editor.apply {
                            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                            finish()
                        }
                    }
                    Toast.makeText(this@LoginActivity, "succesfull", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}