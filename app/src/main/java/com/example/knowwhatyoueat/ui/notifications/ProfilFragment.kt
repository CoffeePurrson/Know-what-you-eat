package com.example.knowwhatyoueat.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.knowwhatyoueat.databinding.FragmentProfilBinding
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.knowwhatyoueat.R
//import kotlinx.android.synthetic.main.fragment_profil.*
import com.example.knowwhatyoueat.databinding.FragmentProfilBinding.inflate

class ProfilFragment : Fragment() {

    var check1: Boolean = false
    var check2: Boolean = false
    var check4: Boolean = false
    var check5: Boolean = false
    var check6: Boolean = false
    var check7: Boolean = false
    var check8: Boolean = false
    var check9: Boolean = false
    var check10: Boolean = false
    var check11: Boolean = false
    var check12: Boolean = false
    var check13: Boolean = false
    var check14: Boolean = false
    var check18: Boolean = false
    var check20: Boolean = false
    var check21: Boolean = false
    private lateinit var profilViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null

    //val testview : TextView = binding.textView
    var check1: Boolean = false
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profilViewModel =
            ViewModelProvider(this).get(ProfilViewModel::class.java)

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sw1: Switch = binding.switch1
/*
        val textView: TextView = binding.textNotifications
        profilViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

 */
        val sw1: Switch = binding.switch1
        val sw2: Switch = binding.switch2
        val sw4: Switch = binding.switch4
        val sw5: Switch = binding.switch5
        val sw6: Switch = binding.switch6
        val sw7: Switch = binding.switch7
        val sw8: Switch = binding.switch8
        val sw9: Switch = binding.switch9
        val sw10: Switch = binding.switch10
        val sw11: Switch = binding.switch11
        val sw12: Switch = binding.switch12
        val sw13: Switch = binding.switch13
        val sw14: Switch = binding.switch14
        val sw18: Switch = binding.switch18
        val sw20: Switch = binding.switch20
        val sw21: Switch = binding.switch21

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(
                activity, "The Switch is " + if (isChecked) "on" else "off",
                Toast.LENGTH_SHORT
            ).show()
            if (isChecked) {
                //do stuff when Switch is ON
                check1 = true
            } else {
                //do stuff when Switch if OFF
                check1 = false
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /* fun onCheckedChanged1(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            activity, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check1 = true
        } else {
            //do stuff when Switch if OFF
            check1 = false
        }

    }
    */







}