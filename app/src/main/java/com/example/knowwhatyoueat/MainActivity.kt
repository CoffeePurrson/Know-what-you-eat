package com.example.knowwhatyoueat

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.knowwhatyoueat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val textView = binding.tvTest

        /*
        binding.buTest.setOnClickListener {
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://world.openfoodfacts.org/api/v0/product/737628064502"

            // Request a string response from the provided URL.
            val stringRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    val productViewActivity = Intent(this, ProductView::class.java)
                    productViewActivity.putExtra("response", response.toString())
                    startActivity(productViewActivity)
                    //textView.text = "${response.toString().substring(0, 500)}"
                },
                Response.ErrorListener { textView.text = "Dat hat nich geklappt :("})

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }
*/

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}