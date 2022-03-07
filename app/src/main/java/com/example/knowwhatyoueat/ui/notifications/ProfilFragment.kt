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
                activity, "The Switch is " + if (isChecked) "on" else "off",
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

        sw_Vegetarisch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Vegetarisch = true
            } else {
                //do stuff when Switch if OFF
                check_Vegetarisch = false
            }
        }

        sw_Ei.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Ei = true
            } else {
                //do stuff when Switch if OFF
                check_Ei = false
            }
        }

        sw_Senf.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Senf = true
            } else {
                //do stuff when Switch if OFF
                check_Senf = false
            }
        }

        sw_Erdnuss.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Erdnuss = true
            } else {
                //do stuff when Switch if OFF
                check_Erdnuss = false
            }
        }

        sw_Soja.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Soja = true
            } else {
                //do stuff when Switch if OFF
                check_Soja = false
            }
        }

        sw_Fisch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Fisch = true
            } else {
                //do stuff when Switch if OFF
                check_Fisch = false
            }
        }

        sw_Weich.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Weich = true
            } else {
                //do stuff when Switch if OFF
                check_Weich = false
            }
        }

        sw_Kruste.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Kruste = true
            } else {
                //do stuff when Switch if OFF
                check_Kruste = false
            }
        }

        sw_Kuh.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Kuh = true
            } else {
                //do stuff when Switch if OFF
                check_Kuh = false
            }
        }

        sw_Lupine.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Lupine = true
            } else {
                //do stuff when Switch if OFF
                check_Lupine = false
            }
        }

        sw_Sellerie.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Sellerie = true
            } else {
                //do stuff when Switch if OFF
                check_Sellerie = false
            }
        }

        sw_Gluten.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Gluten = true
            } else {
                //do stuff when Switch if OFF
                check_Gluten = false
            }
        }

        sw_Schale.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Schale = true
            } else {
                //do stuff when Switch if OFF
                check_Schale = false
            }
        }

        sw_SchUSul.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_SchUSul = true
            } else {
                //do stuff when Switch if OFF
                check_SchUSul = false
            }
        }

        sw_Sesam.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check_Sesam = true
            } else {
                //do stuff when Switch if OFF
                check_Sesam = false
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}