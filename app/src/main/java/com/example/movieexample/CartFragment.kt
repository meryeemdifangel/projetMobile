package com.example.movieexample
import MyAdapter3

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieexample.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private var util = Util()

    private lateinit var viewModel: MenuItemViewModel
    private var data: List<Any> = emptyList()
    private lateinit var sharedPref: SharedPreferences
    private  val PREF_NAME = "user_auth"
    private  val KEY_AUTHENTICATED = "authenticated"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPref = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logout.setOnClickListener {
            // Clear the authentication status from shared preferences
            sharedPref.edit().remove(KEY_AUTHENTICATED).apply()

            // Show a toast message to indicate successful logout
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

            // Navigate back to the login screen or redirect to a new screen as per your app's requirements
            val fragment = LoginFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                android.R.id.content,
                fragment
            )?.addToBackStack(null)?.commit()
        }

        binding.subtotalTv.text = util.getTotal(this.requireContext()).toString() + " DA"

        binding.btnCheckOut.setOnClickListener{
            val authenticated = sharedPref?.getBoolean(KEY_AUTHENTICATED, false) ?: false
            if (authenticated) {
                Toast.makeText(this.requireContext(),"order confirmed",Toast.LENGTH_SHORT).show()
            } else {
                // User is not authenticated, show authentication screen
                val fragment = LoginFragment()
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    android.R.id.content,
                    fragment
                )?.addToBackStack(null)?.commit()
            }

        }
        // Set up recyclerview
        binding.recyclerView1.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView1.adapter = MyAdapter3(util.viewCart(this.requireContext())!!, this)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}