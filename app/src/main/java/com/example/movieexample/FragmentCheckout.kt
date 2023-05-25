package com.example.movieexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import checkoutAdapter
import com.example.movieexample.databinding.FragmentCheckoutBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FragmentCheckout : Fragment() {

    private var util = Util()
    lateinit var binding: FragmentCheckoutBinding

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(layoutInflater)
        val view = binding.root

        // Initialize the MapView
        mapView = binding.userLocationMapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(map: GoogleMap) {
                googleMap = map

                // Add a marker to a specific location
                val initialLocation = LatLng(36.6499974 , 3.1499994)
                googleMap.addMarker(
                    MarkerOptions().position(initialLocation).title("Marker in San Francisco")
                )

                // Move the camera to the initial marker location
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 12f))

                // Set a click listener on the map
                googleMap.setOnMapClickListener { clickedLocation ->
                    // Remove any existing marker
                    googleMap.clear()

                    // Add a new marker at the clicked location
                    googleMap.addMarker(
                        MarkerOptions().position(clickedLocation).title("Selected Location")
                    )
                }
            }
        })

        binding.checkoutSubTotalTv.text = util.getTotal(this.requireContext()).toString() + " DA"
        binding.checkoutMerchant.text = "Restaurant Name"
        binding.checkoutCity.text = "Alger"
        binding.checkoutAddress.text = "Oued Smar"

        binding.editLocation.setOnClickListener {
            binding.searchEditText.visibility = View.VISIBLE
            binding.checkoutCity.visibility = View.INVISIBLE
            binding.checkoutAddress.visibility = View.INVISIBLE
            binding.editLocation.visibility = View.INVISIBLE
        }

        binding.checkoutRv.layoutManager = LinearLayoutManager(this.requireActivity())
        binding.checkoutRv.adapter = checkoutAdapter(util.viewCart(this.requireContext())!!, this)
        return view


    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}