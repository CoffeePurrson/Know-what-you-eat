package com.example.knowwhatyoueat.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.knowwhatyoueat.ProductView
import com.example.knowwhatyoueat.databinding.FragmentSucheBinding

class SucheFragment : Fragment() {

    private lateinit var sucheViewModel: SucheViewModel
    private var _binding: FragmentSucheBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sucheViewModel =
            ViewModelProvider(this).get(SucheViewModel::class.java)

        _binding = FragmentSucheBinding.inflate(layoutInflater)
        val root: View = binding.root
/*
        val textView: TextView = binding.textHome
        sucheViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


 */
        /*
        val textView = binding.tvTest
        binding.buTest.setOnClickListener {
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(requireActivity().getApplicationContext())
            var url = "https://world.openfoodfacts.org/api/v0/product/737628064502"
            //var url = "https://world.openfoodfacts.org/api/v0/product/7613037639858"
            //var url = "https://world.openfoodfacts.org/api/v0/product/4001724038870"
            //var url = "https://world.openfoodfacts.org/api/v0/product/4030387039849"
            //var url = "https://world.openfoodfacts.org/api/v0/product/4000417931009"
            //für Nüsse:
            //var url = "https://world.openfoodfacts.org/api/v0/product/3168930010265"
            //für Krusten- und Weichtiere:
            //var url ="https://world.openfoodfacts.org/api/v0/product/3270160694631"
            //für Fisch:
            //var url ="https://world.openfoodfacts.org/api/v0/product/20155087"
            //für Lupine:
            //var url = "https://world.openfoodfacts.org/api/v0/product/3600900006682"

            // Request a string response from the provided URL.
            val stringRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    val productViewActivity = Intent(getActivity(), ProductView::class.java)
                    productViewActivity.putExtra("response", response.toString())
                    startActivity(productViewActivity)
                    //textView.text = "${response.toString().substring(0, 500)}"
                },
                Response.ErrorListener { textView.text = "Dat hat nich geklappt :("})

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }
        */
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}