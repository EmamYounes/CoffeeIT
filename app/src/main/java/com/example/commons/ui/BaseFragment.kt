package com.example.commons.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.example.ui.coffeeit.R
import com.google.android.material.snackbar.Snackbar


open class BaseFragment : Fragment() {

    var dialog: LoadingDialog? = null

    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = LoadingDialog(requireContext())
    }

    fun showLoading() {
        dialog?.show()
    }

    fun hideLoading() {
        dialog?.dismiss()
    }

    fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).also { snackbar ->
            snackbar.setAction("Ok") {
                snackbar.dismiss()
            }
        }.show()
    }
}