package com.example.krishiapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.krishiapp.R
import com.example.krishiapp.databinding.ActivitySignUpBinding
import com.example.krishiapp.presentation.dashboard.HomeActivity

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setContentView(binding.root)
        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}