package com.example.krishiapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.krishiapp.MainActivity
import com.example.krishiapp.R
import com.example.krishiapp.databinding.ActivitySignUpBinding
import com.example.krishiapp.presentation.dashboard.HomeActivity
import com.example.krishiapp.utils.Utils
import com.example.krishiapp.utils.setRequired
import com.google.android.material.textfield.TextInputLayout

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
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()
                startActivity(Intent(this, HomeActivity::class.java))
                //  if (password >= 6.toString()) {

                //  }
            }
        }
        requiredFields.forEach {
            it.setRequired()
        }
    }
}