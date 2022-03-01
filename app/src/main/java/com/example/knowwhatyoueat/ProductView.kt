package com.example.knowwhatyoueat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.knowwhatyoueat.databinding.ActivityProductViewBinding
import org.json.JSONObject


class ProductView : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding

    @SuppressLint("SetTextI18n")
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
            val  name = foodJSON.getString(("product_name"))
            binding.tvProduktname.text=name.toString()
            val hersteller=foodJSON.getString("brand_owner")
            binding.tvHerstellername.text =hersteller.toString()
            val zutaten=foodJSON.getString("ingredients_text")
            binding.tvInhaltsstoffe.text= zutaten.toString()
            val herstellungsland = foodJSON.getString("origins")
            binding.tvOrigin.text = herstellungsland.toString()

            val portion = foodJSON.getString("serving_quantity")
            binding.tvPortion.text = ("Pro Portion (${portion.toString()}g)")

            val naehrwerte = foodJSON.getJSONObject("nutriments")
            val energie100 = naehrwerte.getString("energy-kcal_100g")
            val energiePortion = naehrwerte.getString("energy-kcal_serving")
            val energieEinheit = naehrwerte.getString("energy-kcal_unit")
            binding.tvEnergie100.text = energie100.toString() + energieEinheit.toString()
            binding.tvEnergiePortion.text = energiePortion.toString() + energieEinheit.toString()
            val kohlenhydrat100 = naehrwerte.getString("carbohydrates_100g")
            val kohlenhydratPortion = naehrwerte.getString("carbohydrates_serving")
            val kohlenhydratEinheit = naehrwerte.getString("carbohydrates_unit")
            binding.tvKohlenhydrat100.text = kohlenhydrat100.toString() + kohlenhydratEinheit.toString()
            binding.tvKohlenhydratPortion.text = kohlenhydratPortion.toString() + kohlenhydratEinheit.toString()
            val fett100 = naehrwerte.getString("fat_100g")
            val fettPortion = naehrwerte.getString("fat_serving")
            val fettEinheit = naehrwerte.getString("fat_unit")
            binding.tvFett100.text = fett100.toString() + fettEinheit.toString()
            binding.tvFettPortion.text = fettPortion.toString() + fettEinheit.toString()
            val fettsaeure100 = naehrwerte.getString("saturated-fat_100g")
            val fettsaeurePortion = naehrwerte.getString("saturated-fat_serving")
            val fettsaeureEinheit = naehrwerte.getString("saturated-fat_unit")
            binding.tvFettsaeuren100.text = fettsaeure100.toString() + fettsaeureEinheit.toString()
            binding.tvFettsaeurenPortion.text = fettsaeurePortion.toString() + fettsaeureEinheit.toString()
            val zucker100 = naehrwerte.getString("sugars_100g")
            val zuckerPortion = naehrwerte.getString("sugars_serving")
            val zuckerEinheit = naehrwerte.getString("sugars_unit")
            binding.tvZucker100.text = zucker100.toString() + zuckerEinheit.toString()
            binding.tvZuckerPortion.text = zuckerPortion.toString() + zuckerEinheit.toString()
            val protein100 = naehrwerte.getString("proteins_100g")
            val proteinPortion = naehrwerte.getString("proteins_serving")
            val proteinEinheit = naehrwerte.getString("proteins_unit")
            binding.tvProtein100.text = protein100.toString() + proteinEinheit.toString()
            binding.tvProteinPortion.text = proteinPortion.toString() + proteinEinheit.toString()
            val salz100 = naehrwerte.getString("salt_100g")
            val salzPortion = naehrwerte.getString("salt_serving")
            val salzEinheit = naehrwerte.getString("salt_unit")
            binding.tvSalz100.text = salz100.toString() + salzEinheit.toString()
            binding.tvSalzPortion.text = salzPortion.toString() + salzEinheit.toString()

            val produktImage = foodJSON.getString("image_front_small_url")
            com.squareup.picasso.Picasso.get().load(produktImage).into(binding.ivProduktImage)

            val novaScore = foodJSON.getString("nova_group")
            if(novaScore == "1")
                binding.ivNovascore.setImageResource(R.drawable.nova_group_1)
            else if(novaScore == "2")
                binding.ivNovascore.setImageResource(R.drawable.nova_group_2)
            else if(novaScore == "3")
                binding.ivNovascore.setImageResource(R.drawable.nova_group_3)
            else if(novaScore=="4")
                binding.ivNovascore.setImageResource(R.drawable.nova_group_4)
            else
                binding.ivNovascore.setImageResource(R.drawable.nova_group_unknown)

            val nutriScore = foodJSON.getString("nutriscore_grade")
            if(nutriScore == "a")
                binding.ivNutriScore.setImageResource(R.drawable.nutriscore_a)
            else if(nutriScore == "b")
                binding.ivNutriScore.setImageResource(R.drawable.nutriscore_b)
            else if(nutriScore == "c")
                binding.ivNutriScore.setImageResource(R.drawable.nutriscore_c)
            else if(nutriScore == "d")
                binding.ivNutriScore.setImageResource(R.drawable.nutriscore_d)
            else if(nutriScore == "e")
                binding.ivNutriScore.setImageResource(R.drawable.nutriscore_e)
            else
                binding.ivNutriScore.setImageResource(R.drawable.nutriscore_unknown)
        }

        binding.Zurueck.setOnClickListener{ finish()  }
    }
}
