package com.example.krishiapp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.krishiapp.R
import com.example.krishiapp.presentation.addFragment

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        addFragment(R.id.frame,DetailFragment())
    }
}