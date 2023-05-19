package com.example.movieexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.movieexample.databinding.FragmentMenuBinding

class MenuItemFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val util = Util()

    private lateinit var viewModel: MenuItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val menuId = arguments?.getInt("menuId") ?: -1
        val menuItem =
            AppDatabase.buildDatabase(requireContext())?.getMenuDao()?.getMenuById(menuId)!!
        //setting the menu attributes
        viewModel = MenuItemViewModel(1,menuItem.price)
        binding.productName.text = menuItem.name
        binding.productPrice.text = menuItem.price.toString() + " DA"
        binding.bottomSheetTextDescription.text = menuItem.description
        binding.bottomSheetProductImg.setImageResource(menuItem.picture)

        binding.increaseQuantity.setOnClickListener {
            viewModel.incrementCount()
            binding.quantity.text = viewModel.getCount().toString()
            val price = viewModel.getPrice()// Replace with the actual price of the product
            val totalPrice = binding.quantity.text.toString().toInt()  * price
            binding.productPrice.text = "$totalPrice DA"
        }

        binding.decreaseQuantity.setOnClickListener {
            if (viewModel.getCount() > 1) {
                viewModel.decrementCount()
                binding.quantity.text = viewModel.getCount().toString()
                val price = viewModel.getPrice()// Replace with the actual price of the product
                val totalPrice = viewModel.getCount() * price
                binding.productPrice.text = "$totalPrice DA"
            }
        }

        binding.btnAddToCart.setOnClickListener {
            val instance = AppDatabase.buildDatabase(this.requireContext())
            val restos = instance?.getMenuDao()?.getAllRestaurants()!!

            val menuId = arguments?.getInt("menuId", -1)!!
            val restaurantId = arguments?.getInt("restaurantId", -1)!!
            if (restos.contains(restaurantId) || restos.isEmpty()) {
                Toast.makeText(this.requireContext(), "Item added to Cart", Toast.LENGTH_SHORT)
                    .show()
                util.addToCart(this.requireContext(), menuId, viewModel.getCount())
            } else {
                Toast.makeText(
                    this.requireContext(),
                    "The cart should contain only one restaurant",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}