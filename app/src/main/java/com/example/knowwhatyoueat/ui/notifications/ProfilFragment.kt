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

    private lateinit var profilViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null
   // val sw1: Switch = binding.switch1
    val testview : TextView = binding.textView
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
/*
        val textView: TextView = binding.textNotifications
        profilViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

 */
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