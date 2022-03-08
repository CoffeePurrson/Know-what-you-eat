package com.example.knowwhatyoueat.ui.notifications

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.knowwhatyoueat.databinding.FragmentProfilBinding
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.knowwhatyoueat.R
//import kotlinx.android.synthetic.main.fragment_profil.*
import com.example.knowwhatyoueat.databinding.FragmentProfilBinding.inflate

class ProfilFragment : Fragment() {

    //var check_Vegan: Boolean = false
    var check_Vegetarisch: Boolean = false
    var check_Ei: Boolean = false
    var check_Senf: Boolean = false
    var check_Erdnuss: Boolean = false
    var check_Soja: Boolean = false
    var check_Fisch: Boolean = false
    var check_Weich: Boolean = false
    var check_Kruste: Boolean = false
    var check_Kuh: Boolean = false
    var check_Lupine: Boolean = false
    var check_Sellerie: Boolean = false
    var check_Gluten: Boolean = false
    var check_Schale: Boolean = false
    var check_SchUSul: Boolean = false
    var check_Sesam: Boolean = false
    private lateinit var profilViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var sharedPrefFile  = "ProfilPreferences"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profilViewModel =
            ViewModelProvider(this).get(ProfilViewModel::class.java)

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
/*
        val textView: TextView = binding.textNotifications
        profilViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

 */

        val ProfilPreferences: SharedPreferences =
            this.requireActivity().getSharedPreferences( sharedPrefFile,MODE_PRIVATE)

        val sw_Vegan: Switch = binding.switchVegan
        val sw_Vegetarisch: Switch = binding.switchVege
        val sw_Ei: Switch = binding.switchEi
        val sw_Senf: Switch = binding.switchSenf
        val sw_Erdnuss: Switch = binding.switchErd
        val sw_Soja: Switch = binding.switchSoja
        val sw_Fisch: Switch = binding.switchFis
        val sw_Weich: Switch = binding.switchWeich
        val sw_Kruste: Switch = binding.switchKruste
        val sw_Kuh: Switch = binding.switchKuh
        val sw_Lupine: Switch = binding.switchLup
        val sw_Sellerie: Switch = binding.switchSell
        val sw_Gluten: Switch = binding.switchGlut
        val sw_Schale: Switch = binding.switchSchale
        val sw_SchUSul: Switch = binding.switchSchUSul
        val sw_Sesam: Switch = binding.switchSes

        val check_Vegan = ProfilPreferences.getBoolean("vegan", false)
        sw_Vegan.setChecked(check_Vegan)

        sw_Vegan.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Vegan wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                //do stuff when Switch is ON
                editor.putBoolean("vegan", true)
                editor.apply()
                editor.commit()
            } else {
                //do stuff when Switch if OFF
                editor.putBoolean("vegan", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Vegetarisch = ProfilPreferences.getBoolean("vegetarisch", false)
        sw_Vegan.setChecked(check_Vegetarisch)

        sw_Vegetarisch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Vegetarisch wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                //do stuff when Switch is ON
                editor.putBoolean("vegetarisch", true)
                editor.apply()
                editor.commit()
            } else {
                //do stuff when Switch if OFF
                editor.putBoolean("vegetarisch", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Ei = ProfilPreferences.getBoolean("ei", false)
        sw_Vegan.setChecked(check_Ei)

        sw_Ei.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Ei wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("ei", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("ei", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Senf = ProfilPreferences.getBoolean("senf", false)
        sw_Vegan.setChecked(check_Senf)

        sw_Senf.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Senf wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("senf", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("senf", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Erdnuss = ProfilPreferences.getBoolean("erdnuss", false)
        sw_Vegan.setChecked(check_Erdnuss)

        sw_Erdnuss.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Erdnuss wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("erdnuss", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("erdnuss", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Soja = ProfilPreferences.getBoolean("soja", false)
        sw_Vegan.setChecked(check_Soja)

        sw_Soja.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Soja wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                /editor.putBoolean("soja", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("soja", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Fisch = ProfilPreferences.getBoolean("fisch", false)
        sw_Vegan.setChecked(check_Fisch)

        sw_Fisch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Fisch wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("fisch", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("fisch", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Weich = ProfilPreferences.getBoolean("weich", false)
        sw_Vegan.setChecked(check_Weich)

        sw_Weich.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Weichtiere wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("weich", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("weich", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Kruste = ProfilPreferences.getBoolean("kruste", false)
        sw_Vegan.setChecked(check_Kruste)

        sw_Kruste.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Krustentiere wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("kruste", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("kruste", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Kuh = ProfilPreferences.getBoolean("kuh", false)
        sw_Vegan.setChecked(check_Kuh)

        sw_Kuh.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Kuhmilch wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("kuh", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("kuh", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Lupine = ProfilPreferences.getBoolean("lupine", false)
        sw_Vegan.setChecked(check_Lupine)

        sw_Lupine.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Lupine wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("lupine", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("lupine", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Sellerie = ProfilPreferences.getBoolean("sellerie", false)
        sw_Vegan.setChecked(check_Sellerie)

        sw_Sellerie.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Sellerie wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("sellerie", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("sellerie", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Gluten = ProfilPreferences.getBoolean("gluten", false)
        sw_Vegan.setChecked(check_Gluten)

        sw_Gluten.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Gluten wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("gluten", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("gluten", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Schale = ProfilPreferences.getBoolean("schale", false)
        sw_Vegan.setChecked(check_Schale)

        sw_Schale.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Schalenfrüchte wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("schale", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("schale", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_SchUSul = ProfilPreferences.getBoolean("SchUSul", false)
        sw_Vegan.setChecked(check_SchUSul)

        sw_SchUSul.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Schwefeldioxid und Sulfide wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("schUSul", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("schUSul", false)
                editor.apply()
                editor.commit()
            }
        }

        val check_Sesam = ProfilPreferences.getBoolean("sesam", false)
        sw_Vegan.setChecked(check_Sesam)

        sw_Sesam.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "Sesam wurde " + if (isChecked) "angewählt" else "abgewählt",
                Toast.LENGTH_SHORT
            ).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean("sesam", true)
                editor.apply()
                editor.commit()
            } else {
                editor.putBoolean("sesam", false)
                editor.apply()
                editor.commit()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}