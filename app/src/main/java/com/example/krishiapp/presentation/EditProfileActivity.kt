package com.example.krishiapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.krishiapp.R
import com.example.krishiapp.databinding.ActivityEditProfileBinding
import com.example.krishiapp.presentation.dashboard.HomeActivity

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_edit_profile)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        binding.edtProfileBtn.setOnClickListener {
            pickImageFromGallery()
        }
    }
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.profile.setImageURI(data?.data)
        }
    }
}