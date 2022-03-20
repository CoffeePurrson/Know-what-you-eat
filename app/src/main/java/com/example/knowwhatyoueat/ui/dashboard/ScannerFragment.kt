package com.example.knowwhatyoueat.ui.dashboard

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.budiyev.android.codescanner.*
import com.example.knowwhatyoueat.ProductView
import com.example.knowwhatyoueat.databinding.FragmentScannerBinding


private const val CAMERA_REQUEST_CODE = 101

class DashboardFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var scanner_view: CodeScannerView
    private lateinit var tv_textview: TextView

    private lateinit var scannerViewModel: ScannerViewModel
    private var _binding: FragmentScannerBinding? = null

    private var url: String = ""
    private var requestFinished: Boolean = false

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scannerViewModel =
            ViewModelProvider(this).get(ScannerViewModel::class.java)

        _binding = FragmentScannerBinding.inflate(layoutInflater)
        val root: View = binding.root

        scanner_view = binding.scannerView
        tv_textview = binding.tvTextView

        requestFinished = false

        setupPermissions()
        codeScanner()

        return root
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(requireActivity(), scanner_view)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                requireActivity().runOnUiThread {
                    // Wird nur bei einem erfolgreichen Scan aufgerufen:
                    tv_textview.text = it.text
                    url = "https://world.openfoodfacts.org/api/v0/product/" + it.text
                    urlRequest()
                }
            }

            errorCallback = ErrorCallback {
                requireActivity().runOnUiThread {
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(requireActivity(),
            android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(this, "You need the camera permission to be able to use this app", Toast.LENGTH_SHORT).show()
                    // Unsuccessful
                } else {
                    // Successful
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun urlRequest() {
        if (url != "" && requestFinished == false) {
            val queue = Volley.newRequestQueue(requireActivity().getApplicationContext())
            val stringRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    if (response.getString("status_verbose")=="product found") {
                        val productViewActivity = Intent(getActivity(), ProductView::class.java)
                        productViewActivity.putExtra("response", response.toString())
                        startActivity(productViewActivity)
                    } else {
                        Toast.makeText(
                            activity, "Produkt nicht gefunden",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                {})
            queue.add(stringRequest)
            requestFinished = true
        }
    }
}
