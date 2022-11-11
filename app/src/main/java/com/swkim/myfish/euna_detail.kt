package com.swkim.myfish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.swkim.myfish.databinding.ActivityEunaDetailBinding

class euna_detail : AppCompatActivity() {
    private lateinit var binding : ActivityEunaDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEunaDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val spinner = binding.spinner

        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, R.layout.spinnerlayout)
    }
}