package com.swkim.myfish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.swkim.myfish.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var backPressedTime : Long = 0

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.searchImage.setOnClickListener {
            startActivity(Intent(this, WorkingActivity::class.java))
            finish()
        }

        binding.fishInfo.setOnClickListener {
            startActivity(Intent(this, gazami_detail::class.java))
            finish()
        }

        binding.helpButton.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_design, null)
            val builder = AlertDialog.Builder(this)
                .setView(dialogView)

            val mAlertDialog = builder.show()
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }

}