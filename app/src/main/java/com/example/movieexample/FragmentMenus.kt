package com.example.movieexample

import MyAdapter2
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieexample.databinding.FragmentMenusBinding

class FragmentMenus : Fragment() {

    lateinit var binding: FragmentMenusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenusBinding.inflate(layoutInflater)
        val view = binding.root

        val restaurantId = this.requireActivity().intent.getIntExtra("restaurantId",1)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerView.adapter = MyAdapter2(AppDatabase.buildDatabase(this.requireContext())?.getMenuDao()?.getRestaurantMenus(restaurantId)!!, this.activity)

        binding.recyclerView.layoutManager = LinearLayoutManager(this.requireActivity())
        if (restaurantId != null) {
            binding.recyclerView.adapter = MyAdapter2(
                AppDatabase.buildDatabase(this.requireContext())?.getMenuDao()
                    ?.getRestaurantMenus(restaurantId)!!, this.requireActivity()
            )
        }
        return view
    }

}