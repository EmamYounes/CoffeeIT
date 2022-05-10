package com.example.ui.coffeeit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.commons.extensions.delay
import com.example.commons.ui.BaseFragment
import com.example.ui.coffeeit.R

class WelcomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        delay(3000) {
            navToExtraFragment()
        }
    }

    private fun navToExtraFragment() {
        val navController =
            activity?.findNavController(R.id.coffee_brew_nav_host_fragment)
        navController?.backQueue?.clear()
        navController?.navigate(R.id.styleFragment, bundleOf(), options)
    }
}