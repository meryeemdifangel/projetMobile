package com.example.movieexample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movieexample.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPref: SharedPreferences
    private  val PREF_NAME = "user_auth"
    private  val KEY_AUTHENTICATED = "authenticated"
    private lateinit var viewModel: MenuItemViewModel
    private var data: List<Any> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPref = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Add an onClickListener to the login button
        binding.btnLogin.setOnClickListener {
            val email = binding.loginEmailInputEditText.text.toString()
            val password = binding.loginPasswordInputEditText.text.toString()

            // Check if the email and password are correct
            if (email == "test@gmail.com" && password == "test") {




                // Save the authentication status to shared preferences
                sharedPref.edit().putBoolean(KEY_AUTHENTICATED, true).apply()

                // Redirect to the order validation interface
                    val fragment = CartFragment()
                    activity?.supportFragmentManager?.beginTransaction()?.replace(
                        android.R.id.content,
                        fragment
                    )?.addToBackStack(null)?.commit()
            } else {
                // Display an error message if the email or password is incorrect
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }




}