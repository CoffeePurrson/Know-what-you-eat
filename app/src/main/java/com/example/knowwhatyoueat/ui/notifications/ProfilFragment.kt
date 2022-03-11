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
import com.example.knowwhatyoueat.databinding.FragmentProfilBinding.bind
//import kotlinx.android.synthetic.main.fragment_profil.*
import com.example.knowwhatyoueat.databinding.FragmentProfilBinding.inflate

class ProfilFragment : Fragment() {
    private lateinit var profilViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //SharedPreferences
    private var sharedPrefFile  = "ProfilPreferences"
    private lateinit var ProfilPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profilViewModel =
            ViewModelProvider(this).get(ProfilViewModel::class.java)

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ProfilPreferences = this.requireActivity().getSharedPreferences( sharedPrefFile,MODE_PRIVATE)

        binding.switchVegan.setChecked(ProfilPreferences.getBoolean("vegan", false))
        switchAuswerten(binding.switchVegan, "vegan")
        binding.switchVege.setChecked(ProfilPreferences.getBoolean("vegetarisch", false))
        switchAuswerten(binding.switchVege, "vegetarisch")
        binding.switchEi.setChecked(ProfilPreferences.getBoolean("ei", false))
        switchAuswerten(binding.switchEi,  "ei")
        binding.switchSenf.setChecked(ProfilPreferences.getBoolean("senf", false))
        switchAuswerten(binding.switchSenf, "senf")
        binding.switchErd.setChecked(ProfilPreferences.getBoolean("erdnuss", false))
        switchAuswerten(binding.switchErd,"erdnuss")
        binding.switchSoja.setChecked(ProfilPreferences.getBoolean("soja", false))
        switchAuswerten(binding.switchSoja, "soja")
        binding.switchFis.setChecked(ProfilPreferences.getBoolean("fisch", false))
        switchAuswerten(binding.switchFis, "fisch")
        binding.switchWeich.setChecked(ProfilPreferences.getBoolean("weich", false))
        switchAuswerten(binding.switchWeich, "weich")
        binding.switchKruste.setChecked(ProfilPreferences.getBoolean("kruste", false))
        switchAuswerten(binding.switchKruste, "kruste")
        binding.switchKuh.setChecked(ProfilPreferences.getBoolean("kuh", false))
        switchAuswerten(binding.switchKuh, "kuh") //WARUM HEIßT MILCH KUH?!?!??!??!??!?!?!?!??!?!? AMERICA EXPLAIN!!!!
        binding.switchLup.setChecked(ProfilPreferences.getBoolean("lupine", false))
        switchAuswerten(binding.switchLup, "lupine")
        binding.switchSell.setChecked(ProfilPreferences.getBoolean("sellerie", false))
        switchAuswerten(binding.switchSell, "sellerie")
        binding.switchGlut.setChecked(ProfilPreferences.getBoolean("gluten", false))
        switchAuswerten(binding.switchGlut, "gluten")
        binding.switchSchale.setChecked(ProfilPreferences.getBoolean("schale", false))
        switchAuswerten(binding.switchSchale, "schale")
        binding.switchSchUSul.setChecked(ProfilPreferences.getBoolean("schUSul", false))
        switchAuswerten(binding.switchSchUSul,"schUSul")
        binding.switchSes.setChecked(ProfilPreferences.getBoolean("sesam", false))
        switchAuswerten(binding.switchSes, "sesam")
        return root
    }

    fun switchAuswerten(
        sw:Switch,
        keyvalue:String) {
        /*
        Voraussetzung: ProfilPreferences zugewiesen
        Ergebnis: Einstellung in SharedPreferences gespeichert
         */
        sw.setOnCheckedChangeListener { _, isChecked ->
            //Toast.makeText(
            //    activity, "Sesam wurde " + if (isChecked) "angewählt" else "abgewählt",
            //    Toast.LENGTH_SHORT
            //).show()
            val editor:SharedPreferences.Editor =  ProfilPreferences.edit()
            if (isChecked) {
                editor.putBoolean(keyvalue, true)
            } else {
                editor.putBoolean(keyvalue, false)
            }
            editor.apply()
            editor.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}