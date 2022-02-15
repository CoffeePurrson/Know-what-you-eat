package com.example.knowwhatyoueat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knowwhatyoueat.databinding.ActivityProductViewBinding

class ProductView : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Zurueck.setOnClickListener{ finish()  }
    }
}