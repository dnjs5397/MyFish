package com.swkim.myfish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.swkim.myfish.databinding.ActivitySsogariDetailBinding

class ssogari_detail : AppCompatActivity() {
    private lateinit var binding : ActivitySsogariDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySsogariDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val spinner = binding.spinner

        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, R.layout.spinnerlayout)
    }
}