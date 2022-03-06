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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.knowwhatyoueat.R
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {

    private lateinit var profilViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null

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

    /* Kommt in MainActivity.kt
    val sw1: Switch = findViewById(R.id.switch1)
    val sw2: Switch = findViewById(R.id.switch2)
    val sw4: Switch = findViewById(R.id.switch4)
    val sw5: Switch = findViewById(R.id.switch5)
    val sw6: Switch = findViewById(R.id.switch6)
    val sw7: Switch = findViewById(R.id.switch7)
    val sw8: Switch = findViewById(R.id.switch8)
    val sw9: Switch = findViewById(R.id.switch9)
    val sw10: Switch = findViewById(R.id.switch10)
    val sw11: Switch = findViewById(R.id.switch11)
    val sw12: Switch = findViewById(R.id.switch12)
    val sw13: Switch = findViewById(R.id.switch13)
    val sw14: Switch = findViewById(R.id.switch14)
    val sw18: Switch = findViewById(R.id.switch18)
    val sw20: Switch = findViewById(R.id.switch20)
    val sw21: Switch = findViewById(R.id.switch21)

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

    fun onCheckedChanged1(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
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


    fun onCheckedChanged2(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check2 = true
        } else {
            //do stuff when Switch if OFF
            check2 = false
        }

    }

    fun onCheckedChanged4(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check4 = true
        } else {
            //do stuff when Switch if OFF
            check4 = false
        }

    }

    fun onCheckedChanged5(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check5 = true
        } else {
            //do stuff when Switch if OFF
            check5 = false
        }

    }

    fun onCheckedChanged6(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check6 = true
        } else {
            //do stuff when Switch if OFF
            check6 = false
        }

    }

    fun onCheckedChanged7(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check7 = true
        } else {
            //do stuff when Switch if OFF
            check7 = false
        }

    }

    fun onCheckedChanged8(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check8 = true
        } else {
            //do stuff when Switch if OFF
            check8 = false
        }

    }

    fun onCheckedChanged9(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check9 = true
        } else {
            //do stuff when Switch if OFF
            check9 = false
        }

    }

    fun onCheckedChanged10(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check10 = true
        } else {
            //do stuff when Switch if OFF
            check10 = false
        }

    }

    fun onCheckedChanged11(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check11 = true
        } else {
            //do stuff when Switch if OFF
            check11 = false
        }

    }

    fun onCheckedChanged12(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check12 = true
        } else {
            //do stuff when Switch if OFF
            check12 = false
        }

    }

    fun onCheckedChanged13(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check13 = true
        } else {
            //do stuff when Switch if OFF
            check13 = false
        }

    }

    fun onCheckedChanged14(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check14 = true
        } else {
            //do stuff when Switch if OFF
            check14 = false
        }

    }

    fun onCheckedChanged18(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check18 = true
        } else {
            //do stuff when Switch if OFF
            check18 = false
        }

    }

    fun onCheckedChanged20(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check20 = true
        } else {
            //do stuff when Switch if OFF
            check20 = false
        }

    }

    fun onCheckedChanged21(buttonView: CompoundButton?, isChecked: Boolean) {
        Toast.makeText(
            this, "The Switch is " + if (isChecked) "on" else "off",
            Toast.LENGTH_SHORT
        ).show()
        if (isChecked) {
            //do stuff when Switch is ON
            check21 = true
        } else {
            //do stuff when Switch if OFF
            check21 = false
        }

    } */

}