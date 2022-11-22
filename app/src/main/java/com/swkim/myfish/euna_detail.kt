package com.swkim.myfish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.swkim.myfish.databinding.ActivityEunaDetailBinding

class euna_detail : AppCompatActivity() {

    private var backPressedTime : Long = 0

    private lateinit var binding : ActivityEunaDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEunaDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.homeButton.setOnClickListener {
            startActivity(Intent(this@euna_detail, MainActivity::class.java))
            finish()
        }

        val spinner = binding.spinner

        spinner.adapter = ArrayAdapter.createFromResource(this, R.array.itemList, R.layout.spinnerlayout)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, gazami_detail::class.java))
                        finish()
                    }
                } else if (position == 2) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, galchi_detail::class.java))
                        finish()
                    }
                }
                else if (position == 3) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, gamsung_detail::class.java))
                        finish()
                    }
                }
                else if (position == 4) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, daegu_detail::class.java))
                        finish()
                    }
                }
                else if (position == 5) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, mal_detail::class.java))
                        finish()
                    }
                }
                else if (position == 6) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, bollak_detail::class.java))
                        finish()
                    }
                }

                else if (position == 7) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, ssogari_detail::class.java))
                        finish()
                    }
                }
                else if (position == 8) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, yeolmok_detail::class.java))
                        finish()
                    }
                }
                else if (position == 9) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, okdom_detail::class.java))
                        finish()
                    }
                }
                else if (position == 10) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, rockfish_detail::class.java))
                        finish()
                    }
                }
                else if (position == 11) {

                }
                else if (position == 12) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, janga_detail::class.java))
                        finish()
                    }
                }
                else if (position == 13) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, jeona_detail::class.java))
                        finish()
                    }
                }
                else if (position == 14) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, jogi_detail::class.java))
                        finish()
                    }
                }
                else if (position == 15) {
                    binding.seeButton.setOnClickListener {
                        startActivity(Intent(this@euna_detail, rat_detail::class.java))
                        finish()
                    }
                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


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