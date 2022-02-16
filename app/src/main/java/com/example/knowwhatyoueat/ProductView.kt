package com.example.knowwhatyoueat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knowwhatyoueat.databinding.ActivityProductViewBinding
import org.json.JSONObject

class ProductView : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val z =
            if (intent.hasExtra("response"))
                intent.getStringExtra("response")
            else
                "Das hat leider nicht funktioniert."

        val jsonObj = JSONObject(z?.substring(z?.indexOf("{"), z?.lastIndexOf("}")+1))
        val foodJSON = jsonObj.getJSONObject("product")

        for (i in 0 until foodJSON.length()) {
            var  name = foodJSON.getString(("product_name"))
            binding.textView18.text=name.toString()
            var hersteller=foodJSON.getString("brand_owner")
            binding.textView20.text =hersteller.toString()
            var zutaten=foodJSON.getString("ingredients_text")
            binding.textView31.text= zutaten.toString()
        }

        binding.Zurueck.setOnClickListener{ finish()  }
    }
}
