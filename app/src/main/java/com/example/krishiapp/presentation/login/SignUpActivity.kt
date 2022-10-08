package com.example.krishiapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.krishiapp.MainActivity
import com.example.krishiapp.R
import com.example.krishiapp.databinding.ActivitySignUpBinding
import com.example.krishiapp.domain.User
import com.example.krishiapp.network.ApiService
import com.example.krishiapp.network.RetrofitHelper
import com.example.krishiapp.network.db.DataBaseHandler
import com.example.krishiapp.network.db.SessionManager
import com.example.krishiapp.presentation.dashboard.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.goneToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        val sessionManager=SessionManager(applicationContext)
        if (sessionManager.isLoggedIn()){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        getLogin()
    }


    private fun getLogin() {
        binding.signUpBtn.setOnClickListener {
            val sessionManager=SessionManager(applicationContext)
            var phone=binding.edtPhone.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()
            if (email.isEmpty() && password.isEmpty() && phone.isEmpty() && confirmPassword.isEmpty()){
                binding.apply {
                    edtEmail.error="required"
                    edtPassword.error="required"
                    edtPhone.error="required"
                    edtConfirmPassword.error="required"
                }
            }
            if (email.isEmpty()){
                binding.edtEmail.error="Enter Email address"
            }
            if (password.isEmpty()){
                binding.edtPassword.error="Please Enter your password"
            }
            if (confirmPassword.isEmpty()){
                binding.edtConfirmPassword.error="Please Enter your confirm password"
            }
            if (phone.isEmpty()){
                binding.edtPhone.error="Please Enter phone number"
            }

            if (confirmPassword!=password) {
                binding.edtConfirmPassword.error = "password should be same"
            }
            if (email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty() && confirmPassword.isNotEmpty() && password==confirmPassword) {

                val user=User(phone,email, password)
                val db=DataBaseHandler(this)
                db.insertData(user)
                startActivity(Intent(this,LoginActivity::class.java))
                sessionManager.setLogin(true)
                    finish()


             /*  val call = RetrofitHelper.getRetroInstance().create(ApiService::class.java)
                    .setDetail(phone, email, password)
                call.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {


                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.d("janvi", "error naaaaai pta")
                        Log.d("janvi", t.message.toString())
                        Toast.makeText(
                            this@SignUpActivity,
                            t.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })*/
            }


        }

    }
}