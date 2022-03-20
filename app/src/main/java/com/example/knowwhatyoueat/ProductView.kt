package com.example.knowwhatyoueat

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.knowwhatyoueat.databinding.ActivityProductViewBinding
import org.json.JSONArray
import org.json.JSONObject


class ProductView : AppCompatActivity() {

    //Binding
    private lateinit var binding: ActivityProductViewBinding

    //SharedPreferences
    private var sharedPrefFile  = "ProfilPreferences"
    private lateinit var ProfilPreferences: SharedPreferences

    //API-Response
    private lateinit var jsonObj:JSONObject
    private lateinit var jsonProduct:JSONObject
    private lateinit var jsonAllergensTags:JSONArray
    private lateinit var jsonNaehrwerte:JSONObject
    private lateinit var jsonLifestyleTags:JSONArray

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SharedPreferences laden
        ProfilPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        //API-Response
        val z = intent.getStringExtra("response")
        jsonObj = JSONObject(z?.substring(z?.indexOf("{"), z?.lastIndexOf("}") + 1))
        jsonProduct = jsonObj.getJSONObject("product")
        jsonAllergensTags = jsonProduct.getJSONArray("allergens_tags")
        jsonNaehrwerte = jsonProduct.getJSONObject("nutriments")
        jsonLifestyleTags = jsonProduct.getJSONArray("ingredients_analysis_tags")

        //Allergiefilter
        filter("kuh", "en:milk", "Milch")
        filter("ei", "en:eggs", "Ei")
        filter("erdnuss", "en:peanuts", "Erdnuss")
        filter("fisch", "en:fish", "Fisch")
        filter("kruste", "en:crustaceans", "Krustentiere")
        filter("lupine", "en:lupin", "Lupine")
        filter("gluten", "en:gluten", "Glutenhaltiges Getreide")
        filter("schale", "en:nuts", "Schalenfrüchte")
        filter("sesam", "en:sesame-seeds", "Sesamsamen")
        filter("senf", "en:mustard","Senf")
        filter("soja", "en:soybeans", "Sojabohnen")
        filter("weich", "en:molluscs", "Weichtiere")
        filter("sellerie", "en:celery", "Sellerie")

        //Lifestylefilter
        lifestyleFilter()

        //Darstellung der Daten aus der Response
        setProduktname()
        setAllgemeineInformation("brands", binding.tvHerstellername)
        setAllgemeineInformation("origins", binding.tvOrigin)
        setInhaltsstoffe()
        setPortion()
        setNaehrwerte("energy-kcal_100g","energy-kcal_unit", binding.tvEnergie100)
        setNaehrwerte("energy-kcal_serving","energy-kcal_unit",binding.tvEnergiePortion)
        setNaehrwerte("carbohydrates_100g","carbohydrates_unit",binding.tvKohlenhydrat100)
        setNaehrwerte("carbohydrates_serving","carbohydrates_unit",binding.tvKohlenhydratPortion)
        setNaehrwerte("fat_100g","fat_unit",binding.tvFett100)
        setNaehrwerte("fat_serving","fat_unit",binding.tvFettPortion)
        setNaehrwerte("saturated-fat_100g","saturated-fat_unit",binding.tvFettsaeuren100)
        setNaehrwerte("saturated-fat_serving","saturated-fat_unit",binding.tvFettsaeurenPortion)
        setNaehrwerte("sugars_100g","sugars_unit",binding.tvZucker100)
        setNaehrwerte("sugars_serving","sugars_unit",binding.tvZuckerPortion)
        setNaehrwerte("proteins_100g","proteins_unit",binding.tvProtein100)
        setNaehrwerte("proteins_serving","proteins_unit",binding.tvProteinPortion)
        setNaehrwerte("salt_100g","salt_unit",binding.tvSalz100)
        setNaehrwerte("salt_serving","salt_unit",binding.tvSalzPortion)

        //Bilder/Scores
        setProduktBild()
        setNutri()
        setNova()
        setEco()

        binding.Zurueck.setOnClickListener { finish() }
    }

    fun setProduktname(){
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis: Name des Produkts wird in der ProduktView angezeigt.
        Hinweise: Eine deutsche Bezeichnung wird vorgezogen, wenn diese nicht existiert wird die englische Bezeichnung vorgezogen, wenn diese nicht existiert
        wird die allgemeine Bezeichnung verwendet. Falls der String leer ist, wird "unknown" angezeigt.
         */
        var name=""
        if (jsonProduct.has("product_name_de")) {
            name = jsonProduct.getString(("product_name_de"))
        } else if (jsonProduct.has("product_name_en")){
            name = jsonProduct.getString(("product_name_en"))
        } else if (jsonProduct.has("product_name")){
            name = jsonProduct.getString(("product_name"))
        }
        if (name ==""){
            name="unknown"
        }
        binding.tvProduktname.text = name
    }

    fun setInhaltsstoffe(){
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis:Liste der Inhaltsstoffe wird in der ProduktView angezeigt.
        Hinweise: Eine deutsche Bezeichnung wird vorgezogen, wenn diese nicht existiert wird die englische Bezeichnung vorgezogen, wenn diese nicht existiert
        wird die allgemeine Bezeichnung verwendet. Falls der String leer ist, wird "unknown" angezeigt.
         */
        var inhaltsstoffe=""
        if (jsonProduct.has("ingredients_text_de")) {
            inhaltsstoffe = jsonProduct.getString(("ingredients_text_de"))
        } else if (jsonProduct.has("ingredients_text_en")){
            inhaltsstoffe = jsonProduct.getString(("ingredients_text_en"))
        } else if (jsonProduct.has("ingredients_text")){
            inhaltsstoffe = jsonProduct.getString(("ingredients_text"))
        }
        if (inhaltsstoffe ==""){
            inhaltsstoffe="unknown"
        }
        binding.tvInhaltsstoffe.text = inhaltsstoffe
    }

    fun setAllgemeineInformation(
        attribute:String,
        tv:TextView) {
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis: Wenn das übergebene Attribut für das Produkt in der DB existiert, wird in der übergebenenen Textview der Wert des Attributs angezeigt. Ansonsten
        wird "unknown" angezeigt.
        Hinweis: Für Name des Herstellers, Herkunftsland, Liste der Inhaltsstoffe
         */
        var tmp=""
        if (jsonProduct.has(attribute)){
            tmp=jsonProduct.getString(attribute)
        }
        //Bei manchen Produkten kann es sein, dass das Attribut zwar vorhanden, der dazugehöriger Wert aber ein leerer String ist. Daher dieser zusätzliche If-Ausdruck
        if (tmp ==""){
            tmp="unknown"
        }
        tv.text=tmp
    }

    fun setPortion() {
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis: Portionsangabe wird angezeigt
         */
        var tmp=""
        if (jsonProduct.has("serving_size")){
            tmp=jsonProduct.getString("serving_size")
        }
        if (tmp ==""){
            tmp="unknown"
        }
        binding.tvPortion.text="Pro Portion (${tmp})"
    }

    fun setNaehrwerte(
        attribut:String,
        attributUnit:String,
        tv:TextView) {
        /*
        Voraussetzung: binding und jsonNaehrwerte zugewiesen
        Ergebnis: Nährwerte pro 100g/Portion (abhängig von den übergebenen Attributen) angezeigt
         */
        var tmp=""
        var unit=""
        if (jsonNaehrwerte.has(attribut) && jsonNaehrwerte.has(attributUnit)) {
            tmp = jsonNaehrwerte.getString(attribut)
            unit = jsonNaehrwerte.getString(attributUnit)
        }
        if (tmp==""){
            tmp="unknown"
        }
        tv.text = "${tmp}${unit}"
    }

    fun setProduktBild(){
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis: Wenn ein Bild vorhanden ist, wird es in der Produktview dargestellt.
         */
        if (jsonProduct.has("image_front_small_url")) {
            val produktImage = jsonProduct.getString("image_front_small_url")
            com.squareup.picasso.Picasso.get().load(produktImage).into(binding.ivProduktImage)
        }
    }

    fun setNova(){
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis: Darstellung des Novascores
         */
        if (jsonProduct.has("nova_group")) {
            val novaScore = jsonProduct.getString("nova_group")
            when(novaScore){
                "1" -> binding.ivNovascore.setImageResource(R.drawable.nova_group_1)
                "2" -> binding.ivNovascore.setImageResource(R.drawable.nova_group_2)
                "3" -> binding.ivNovascore.setImageResource(R.drawable.nova_group_3)
                "4" -> binding.ivNovascore.setImageResource(R.drawable.nova_group_4)
            }
        } else {
            binding.ivNovascore.setImageResource(R.drawable.nova_group_unknown)
        }
    }

    fun setNutri(){
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis: Darstellung des Nutriscores
         */
        if (jsonProduct.has("nutriscore_grade")) {
            val nutriScore = jsonProduct.getString("nutriscore_grade")
            when(nutriScore) {
                "a" -> binding.ivNutriScore.setImageResource(R.drawable.nutriscore_a)
                "b" -> binding.ivNutriScore.setImageResource(R.drawable.nutriscore_b)
                "c" -> binding.ivNutriScore.setImageResource(R.drawable.nutriscore_c)
                "d" -> binding.ivNutriScore.setImageResource(R.drawable.nutriscore_d)
                "e" -> binding.ivNutriScore.setImageResource(R.drawable.nutriscore_e)
            }
        } else {
            binding.ivNutriScore.setImageResource(R.drawable.nutriscore_unknown)
        }
    }

    fun setEco() {
        /*
        Voraussetzung: binding und jsonProduct zugewiesen
        Ergebnis: Darstellung des Ecoscores
         */
        if (jsonProduct.has("ecoscore_grade")) {
            val ecoScore = jsonProduct.getString("ecoscore_grade")
            when(ecoScore){
                "a" -> binding.ivEcoscore.setImageResource(R.drawable.ecoscore_a)
                "b" -> binding.ivEcoscore.setImageResource(R.drawable.ecoscore_b)
                "c" -> binding.ivEcoscore.setImageResource(R.drawable.ecoscore_c)
                "d" -> binding.ivEcoscore.setImageResource(R.drawable.ecoscore_d)
                "e" -> binding.ivEcoscore.setImageResource(R.drawable.ecoscore_e)
                "unknown" -> binding.ivEcoscore.setImageResource(R.drawable.ecoscore_unknown)
            }
        } else {
            binding.ivEcoscore.setImageResource(R.drawable.ecoscore_unknown)
        }
    }

    fun filter(
        keyValue: String,
        allergenTag: String,
        allergenName: String) {
        /*
        Voraussetzung: binding, ProfilPreferences und jsonAllergensTags zugewiesen
        Ergebnis: Falls Allergien im Profil angegeben wurden und das gescannte Produkt eines dieser Allergene enthält, wird eine Warnung erstellt und in der Produktview angezeigt
         */
        if (ProfilPreferences.getBoolean(keyValue, false)) {
            for (i in 0 until jsonAllergensTags.length()) {
                if(jsonAllergensTags[i] == allergenTag){
                    neueMeldung("allergie",allergenName)
                }
            }
        }
    }

    fun lifestyleFilter() {
        /*
        Voraussetzung: binding, ProfilPreferences und jsonAllergensTags zugewiesen
        Ergebnis: Falls Lifestyleeinstellungen im Profil angegeben wurden und das gescannte Produkt nicht dem Lifestyle enthält, wird eine Warnung erstellt und in der Produktview angezeigt
         */
        if (ProfilPreferences.getBoolean("vegan", false)) {
            for (i in 0 until jsonLifestyleTags.length()) {
                when(jsonLifestyleTags[i]) {
                    "en:non-vegan" -> neueMeldung("lifestyle","Nicht vegan!")
                    "en:vegan-status-unknown" -> neueMeldung("lifestyle","Vielleicht vegan!")
                    "en:maybe-vegan" -> neueMeldung("lifestyle","Vielleicht vegan!")
                }
            }
        }
        if (ProfilPreferences.getBoolean("vegetarisch", false)) {
            for (i in 0 until jsonLifestyleTags.length()) {
                when(jsonLifestyleTags[i]) {
                    "en:non-vegetarian" -> neueMeldung("lifestyle","Nicht vegetarisch!")
                    "en:vegetarian-status-unknown" -> neueMeldung("lifestyle","Vielleicht vegetarisch!")
                    "en:maybe-vegetarian" -> neueMeldung("lifestyle","Vielleicht vegetarisch!")
                }
            }
        }
    }

    fun neueMeldung(
        mode:String,
        name:String){
        /*
        Voraussetzung: binding zugewiesen
        Ergebnis: Je nach Modus wurde eine Allergie- oder eine Livestylewarnung erstellt.
         */
        val bindingWarnungen = binding.includeWarnungen
        val layout:LinearLayout = bindingWarnungen.warnungenLayout
        //ConstraintLayout für neue Warnung erstellen
        val neuesLayout = ConstraintLayout(this)
        neuesLayout.setId(R.id.vWarnung)

        //Imageview für Warnungssymbol erstellen
        val warnungBild = ImageView(this)
        warnungBild.setId(R.id.ivWarnung)
        warnungBild.setImageResource(resources.getIdentifier("@android:drawable/ic_dialog_alert", null, packageName))
        if (mode=="lifestyle") {
            warnungBild.setColorFilter(ContextCompat.getColor(this,R.color.lifestyleWarnung))
        } else {
            warnungBild.setColorFilter(ContextCompat.getColor(this, R.color.allergieWarnung))
        }
        warnungBild.scaleType=ImageView.ScaleType.FIT_XY
        //Layoutparameter für Imageview
        val lP_warnungBild = ConstraintLayout.LayoutParams(50,50)
        lP_warnungBild.marginStart=20
        warnungBild.setLayoutParams(lP_warnungBild)

        //Warnung
        val warnung = TextView(this)
        warnung.setId(R.id.tvWarnung)
        if (mode=="lifestyle") {
            warnung.text="$name"
        } else {
            warnung.text = "Enthält: $name!"
        }
        //Layoutparameter für Textview
        val lP_warnung = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        lP_warnung.marginStart=20
        warnung.setLayoutParams(lP_warnung)

        //Imageview und Textview zu Constraintlayout hinzufügen
        neuesLayout.addView(warnungBild)
        neuesLayout.addView(warnung)

        //Constraints hinzufügen
        val cs = ConstraintSet()
        cs.clone(neuesLayout)
        cs.connect(warnungBild.getId(), ConstraintSet.START, neuesLayout.getId(), ConstraintSet.START)
        cs.connect(warnung.getId(), ConstraintSet.START, warnungBild.getId(), ConstraintSet.END)
        cs.connect(warnung.getId(), ConstraintSet.TOP, warnungBild.getId(), ConstraintSet.TOP)
        cs.connect(warnung.getId(), ConstraintSet.TOP, warnungBild.getId(), ConstraintSet.TOP)
        cs.applyTo(neuesLayout)
        layout.addView(neuesLayout)
    }
}
