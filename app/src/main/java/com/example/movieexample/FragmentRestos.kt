package com.example.movieexample

import MyAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieexample.databinding.FragmentRestosBinding

class FragmentRestos : Fragment() {

    lateinit var binding: FragmentRestosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRestosBinding.inflate(layoutInflater)
        val view = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(this.requireActivity())
        binding.recyclerView.adapter =MyAdapter(loadData())
        return view
    }

    private fun loadData():List<Restaurant> {
        val data = mutableListOf<Restaurant>()
        data.add(Restaurant(1,"Casa Mono",R.drawable.resto,"Hello","52 Irving Pl, New York, NY 10003",-73.9877,
            40.7359,"Spanish",4.5f,100,"212-253-2773","info@casamononyc.com",
            "https://www.facebook.com/CasaMonoNYC/","https://www.instagram.com/casamononyc/"
        ))
        data.add(Restaurant(2,"Casa Mono 1",R.drawable.mcdo,"Hello","52 Irving Pl, New York, NY 10003",-73.9877,
            40.7359,"Spanish",4.5f,100,"212-253-2773","info@casamononyc.com",
            "https://www.facebook.com/CasaMonoNYC/","https://www.instagram.com/casamononyc/"
        ))
        data.add(Restaurant(3,"Casa Mono 3",R.drawable.pancake,"Hello","52 Irving Pl, New York, NY 10003",-73.9877,
            40.7359,"Spanish",4.5f,100,"212-253-2773","info@casamononyc.com",
            "https://www.facebook.com/CasaMonoNYC/","https://www.instagram.com/casamononyc/"
        ))
        return data
    }
}