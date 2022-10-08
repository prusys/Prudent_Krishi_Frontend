package com.example.krishiapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.example.krishiapp.R
import com.example.krishiapp.databinding.ActivityEditProfileBinding
import com.example.krishiapp.domain.User
import com.example.krishiapp.network.db.DataBaseHandler
import com.example.krishiapp.presentation.EditProfileActivity.Companion.IMAGE_REQUEST_CODE
import com.example.krishiapp.presentation.dashboard.HomeActivity

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    private var users:User?=null
    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_edit_profile)
        setContentView(binding.root)
        users= User()
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        binding.edtProfileBtn.setOnClickListener {
            pickImageFromGallery()
        }
        val email=intent.getStringExtra("Em")
        val phone=intent.getStringExtra("ph")
        val password=intent.getStringExtra("ps")

        binding.apply {
            edtem.setText(email)
            edtph.setText(phone)
            edtps.setText(password)
        }

        updateValues()
    }

    private fun updateValues() {
        binding.saveUpdateBtn.setOnClickListener {
            val db = DataBaseHandler(this)
            var edtphone = binding.edtph.text.toString()
            var edtemail = binding.edtem.text.toString()
            val edtpassword = binding.edtps.text.toString()
            if (edtphone == users?.phone && edtemail == users?.email) {
                Toast.makeText(this, "Record is not changed..", Toast.LENGTH_SHORT).show()
            }
            if (users==null){
                Toast.makeText(this, "Record is null", Toast.LENGTH_SHORT).show()
            }
                val user = User()
                    user.id=users!!.id
                    user.phone = edtphone
                    user.email = edtemail
                    user.password = edtpassword
            db.updateData(user)

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