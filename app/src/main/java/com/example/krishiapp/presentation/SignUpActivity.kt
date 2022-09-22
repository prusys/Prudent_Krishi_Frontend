package com.example.krishiapp.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.krishiapp.presentation.dashboard.HomeActivity
import com.example.krishiapp.utils.Utils
import com.example.krishiapp.utils.setRequired
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private val requiredFields: List<TextInputLayout> by lazy {
        listOf(
            binding.layoutEmail,
            binding.layoutPassword,
            binding.layoutPhone,
            binding.layoutConfirmPassword
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setContentView(binding.root)
        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        getLogin()
    }

    private fun getLogin() {
        binding.signUpBtn.setOnClickListener {
            if (!Utils.isAnyFieldEmpty(requiredFields)) {
                val phone=binding.edtPhone.text.toString()
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()
                val call=RetrofitHelper.getRetroInstance().create(ApiService::class.java)
                    .setDetail(phone,email, password)
                call.enqueue(object :Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        Toast.makeText(this@SignUpActivity, "succesfull", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
        requiredFields.forEach {
            it.setRequired()
        }
    }
}