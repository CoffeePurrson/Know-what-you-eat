package com.example.knowwhatyoueat

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.example.knowwhatyoueat.databinding.ActivityProductViewBinding
import org.json.JSONArray
import org.json.JSONObject


class ProductView : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding
    private var sharedPrefFile  = "ProfilPreferences"
    //val ProfilPreferences: SharedPreferences =
    //    getSharedPreferences( sharedPrefFile, MODE_PRIVATE)
    //getSharedPreferences( sharedPrefFile, MODE_PRIVATE)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var booleanForAllergenView1: Boolean = false
        var booleanForAllergenView2: Boolean = false

        val ProfilPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        val z =
            if (intent.hasExtra("response"))
                intent.getStringExtra("response")
            else
                "Das hat leider nicht funktioniert."

        val jsonObj = JSONObject(z?.substring(z?.indexOf("{"), z?.lastIndexOf("}") + 1))
        val foodJSON = jsonObj.getJSONObject("product")

        val allergensTags = foodJSON.getJSONArray("allergens_tags")

        fun filter(
            keyValue: String,
            allergenTag: String,
            allergenArray: JSONArray,
            allergenName: String) {
            if (ProfilPreferences.getBoolean(keyValue, false)) {
                val bindingWarnungen = binding.includeWarnungen
                val layout:LinearLayout = bindingWarnungen.warnungenLayout
                for (i in 0 until allergenArray.length()) {
                    if(allergenArray[i] == allergenTag){
                        //Horizontales LinearLayout erstellen
                        val neuesLayout = LinearLayout(this)
                        neuesLayout.setOrientation(LinearLayout.HORIZONTAL)
                        //Imageview erstellen
                        val warnungBild = ImageView(this)
                        warnungBild.setImageResource(resources.getIdentifier("@android:drawable/ic_dialog_alert", null, packageName))
                        warnungBild.setColorFilter(ContextCompat.getColor(this,R.color.allergieWarnung))
                        warnungBild.scaleType=ImageView.ScaleType.FIT_XY
                        val layoutParameter = LinearLayout.LayoutParams(50,50)
                        warnungBild.setLayoutParams(layoutParameter)
                        //Warnungstext erstellen
                        val warnung = TextView(this)
                        warnung.text="Enthält: $allergenName!"
                        //Warnungstext zu layout hinzufügen
                        neuesLayout.addView(warnungBild)
                        neuesLayout.addView(warnung)
                        layout.addView(neuesLayout)
                    }
                }
            }
        }

        filter("kuh", "en:milk", allergensTags, "Milch")
        filter("ei", "en:eggs", allergensTags, "Ei")
        filter("erdnuss", "en:peanuts", allergensTags, "Erdnuss")
        filter("fisch", "en:fish", allergensTags, "Fisch")
        filter("kruste", "en:crustaceans", allergensTags, "Krustentiere")
        filter("lupine", "en:lupin", allergensTags, "Lupine")
        filter("gluten", "en:gluten", allergensTags, "Glutenhaltiges Getreide")
        filter("schale", "en:nuts", allergensTags, "Schalenfrüchte")
        filter("sesam", "en:sesame-seeds", allergensTags, "Sesamsamen")
        filter("senf", "en:mustard", allergensTags, "Senf")
        filter("soja", "en:soybeans", allergensTags, "Sojabohnen")
        filter("weich", "en:molluscs", allergensTags, "Weichtiere")
        filter("sellerie", "en:celery", allergensTags, "Sellerie")
        //filter("schUSul", "???", allergensTags, "Schwefeldioxid und Sulfide")

        for (i in 0 until foodJSON.length()) {
            if (foodJSON.has("product_name")) {
                val name = foodJSON.getString(("product_name"))
                binding.tvProduktname.text = name.toString()
            } else
                binding.tvProduktname.text = "unknown"

            if (foodJSON.has("brands")) {
                val hersteller = foodJSON.getString("brands")
                binding.tvHerstellername.text = hersteller.toString()
            } else
                binding.tvHerstellername.text = "unknown"

            if (foodJSON.has("ingredients_text")) {
                val zutaten = foodJSON.getString("ingredients_text")
                binding.tvInhaltsstoffe.text = zutaten.toString()
            } else
                binding.tvInhaltsstoffe.text = "unknown"

            if (foodJSON.has("origins")) {
                val herstellungsland = foodJSON.getString("origins")
                binding.tvOrigin.text = herstellungsland.toString()
            } else
                binding.tvOrigin.text = "unknown"


            if (foodJSON.has("serving_quantity")) {
                val portion = foodJSON.getString("serving_quantity")
                binding.tvPortion.text = ("Pro Portion (${portion.toString()}g)")
            }

            try {
                val naehrwerte = foodJSON.getJSONObject("nutriments")
                if (naehrwerte.has("energy-kcal_100g") && naehrwerte.has("energy-kcal_unit")) {
                    val energie100 = naehrwerte.getString("energy-kcal_100g")
                    val energieEinheit = naehrwerte.getString("energy-kcal_unit")
                    binding.tvEnergie100.text = energie100.toString() + energieEinheit.toString()
                } else
                    binding.tvEnergie100.text = "unknown"
                if (naehrwerte.has("energy-kcal_serving") && naehrwerte.has("energy-kcal_unit")) {
                    val energiePortion = naehrwerte.getString("energy-kcal_serving")
                    val energieEinheit = naehrwerte.getString("energy-kcal_unit")
                    binding.tvEnergiePortion.text =
                        energiePortion.toString() + energieEinheit.toString()
                } else
                    binding.tvEnergiePortion.text = "unknown"

                if (naehrwerte.has("carbohydrates_100g") && naehrwerte.has("energy-kcal_unit")) {
                    val kohlenhydrat100 = naehrwerte.getString("carbohydrates_100g")
                    val kohlenhydratEinheit = naehrwerte.getString("carbohydrates_unit")
                    binding.tvKohlenhydrat100.text =
                        kohlenhydrat100.toString() + kohlenhydratEinheit.toString()
                } else
                    binding.tvKohlenhydrat100.text = "unknown"
                if (naehrwerte.has("carbohydrates_serving") && naehrwerte.has("carbohydrates_unit")) {
                    val kohlenhydratPortion = naehrwerte.getString("carbohydrates_serving")
                    val kohlenhydratEinheit = naehrwerte.getString("carbohydrates_unit")
                    binding.tvKohlenhydratPortion.text =
                        kohlenhydratPortion.toString() + kohlenhydratEinheit.toString()
                } else
                    binding.tvKohlenhydratPortion.text = "unknown"

                if (naehrwerte.has("fat_100g") && naehrwerte.has("fat_unit")) {
                    val fett100 = naehrwerte.getString("fat_100g")
                    val fettEinheit = naehrwerte.getString("fat_unit")
                    binding.tvFett100.text = fett100.toString() + fettEinheit.toString()
                } else
                    binding.tvFett100.text = "unknown"
                if (naehrwerte.has("fat_serving") && naehrwerte.has("fat_unit")) {
                    val fettPortion = naehrwerte.getString("fat_serving")
                    val fettEinheit = naehrwerte.getString("fat_unit")
                    binding.tvFettPortion.text = fettPortion.toString() + fettEinheit.toString()
                } else
                    binding.tvFettPortion.text = "unknown"

                if (naehrwerte.has("saturated-fat_100g") && naehrwerte.has("saturated-fat_unit")) {
                    val fettsaeure100 = naehrwerte.getString("saturated-fat_100g")
                    val fettsaeureEinheit = naehrwerte.getString("saturated-fat_unit")
                    binding.tvFettsaeuren100.text =
                        fettsaeure100.toString() + fettsaeureEinheit.toString()
                } else
                    binding.tvFettsaeuren100.text = "unknown"
                if (naehrwerte.has("saturated-fat_serving") && naehrwerte.has("saturated-fat_unit")) {
                    val fettsaeurePortion = naehrwerte.getString("saturated-fat_serving")
                    val fettsaeureEinheit = naehrwerte.getString("saturated-fat_unit")
                    binding.tvFettsaeurenPortion.text =
                        fettsaeurePortion.toString() + fettsaeureEinheit.toString()
                } else
                    binding.tvFettsaeurenPortion.text = "unknown"

                if (naehrwerte.has("sugars_100g") && naehrwerte.has("sugars_unit")) {
                    val zucker100 = naehrwerte.getString("sugars_100g")
                    val zuckerEinheit = naehrwerte.getString("sugars_unit")
                    binding.tvZucker100.text = zucker100.toString() + zuckerEinheit.toString()
                } else
                    binding.tvZucker100.text = "unknown"
                if (naehrwerte.has("sugars_serving") && naehrwerte.has("sugars_unit")) {
                    val zuckerPortion = naehrwerte.getString("sugars_serving")
                    val zuckerEinheit = naehrwerte.getString("sugars_unit")
                    binding.tvZuckerPortion.text =
                        zuckerPortion.toString() + zuckerEinheit.toString()
                } else
                    binding.tvZuckerPortion.text = "unknown"

                if (naehrwerte.has("proteins_100g") && naehrwerte.has("proteins_unit")) {
                    val protein100 = naehrwerte.getString("proteins_100g")
                    val proteinEinheit = naehrwerte.getString("proteins_unit")
                    binding.tvProtein100.text = protein100.toString() + proteinEinheit.toString()
                } else
                    binding.tvProtein100.text = "unknown"
                if (naehrwerte.has("proteins_serving") && naehrwerte.has("proteins_unit")) {
                    val proteinPortion = naehrwerte.getString("proteins_serving")
                    val proteinEinheit = naehrwerte.getString("proteins_unit")
                    binding.tvProteinPortion.text =
                        proteinPortion.toString() + proteinEinheit.toString()
                } else
                    binding.tvProteinPortion.text = "unknown"

                if (naehrwerte.has("salt_100g") && naehrwerte.has("salt_unit")) {
                    val salz100 = naehrwerte.getString("salt_100g")
                    val salzEinheit = naehrwerte.getString("salt_unit")
                    binding.tvSalz100.text = salz100.toString() + salzEinheit.toString()
                } else
                    binding.tvSalz100.text = "unknown"
                if (naehrwerte.has("salt_serving") && naehrwerte.has("salt_unit")) {
                    val salzPortion = naehrwerte.getString("salt_serving")
                    val salzEinheit = naehrwerte.getString("salt_unit")
                    binding.tvSalzPortion.text = salzPortion.toString() + salzEinheit.toString()
                } else
                    binding.tvSalzPortion.text = "unknown"
            } catch (e: Exception) {

            }

            if (foodJSON.has("image_front_small_url")) {
                val produktImage = foodJSON.getString("image_front_small_url")
                com.squareup.picasso.Picasso.get().load(produktImage).into(binding.ivProduktImage)
            }

            if (foodJSON.has("nova_group")) {
                val novaScore = foodJSON.getString("nova_group")
                if (novaScore == "1")
                    binding.ivNovascore.setImageResource(R.drawable.nova_group_1)
                else if (novaScore == "2")
                    binding.ivNovascore.setImageResource(R.drawable.nova_group_2)
                else if (novaScore == "3")
                    binding.ivNovascore.setImageResource(R.drawable.nova_group_3)
                else if (novaScore == "4")
                    binding.ivNovascore.setImageResource(R.drawable.nova_group_4)
            } else
                binding.ivNovascore.setImageResource(R.drawable.nova_group_unknown)

            if (foodJSON.has("nutriscore_grade")) {
                val nutriScore = foodJSON.getString("nutriscore_grade")
                if (nutriScore == "a")
                    binding.ivNutriScore.setImageResource(R.drawable.nutriscore_a)
                else if (nutriScore == "b")
                    binding.ivNutriScore.setImageResource(R.drawable.nutriscore_b)
                else if (nutriScore == "c")
                    binding.ivNutriScore.setImageResource(R.drawable.nutriscore_c)
                else if (nutriScore == "d")
                    binding.ivNutriScore.setImageResource(R.drawable.nutriscore_d)
                else if (nutriScore == "e")
                    binding.ivNutriScore.setImageResource(R.drawable.nutriscore_e)
            } else
                binding.ivNutriScore.setImageResource(R.drawable.nutriscore_unknown)

            if (foodJSON.has("ecoscore_grade")) {
                val ecoScore = foodJSON.getString("ecoscore_grade")
                if (ecoScore == "a")
                    binding.ivEcoscore.setImageResource(R.drawable.ecoscore_a)
                else if (ecoScore == "b")
                    binding.ivEcoscore.setImageResource(R.drawable.ecoscore_b)
                else if (ecoScore == "c")
                    binding.ivEcoscore.setImageResource(R.drawable.ecoscore_c)
                else if (ecoScore == "d")
                    binding.ivEcoscore.setImageResource(R.drawable.ecoscore_d)
                else if (ecoScore == "e")
                    binding.ivEcoscore.setImageResource(R.drawable.ecoscore_e)
                else
                    binding.ivEcoscore.setImageResource(R.drawable.ecoscore_unknown)
            } else
                binding.ivEcoscore.setImageResource(R.drawable.ecoscore_unknown)

        }

        binding.Zurueck.setOnClickListener { finish() }
    }
}
