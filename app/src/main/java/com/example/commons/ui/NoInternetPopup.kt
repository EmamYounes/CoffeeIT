package com.example.commons.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.commons.utilities.Constant
import com.example.ui.coffeeit.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoInternetPopup : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.no_internet_popup_layout, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        with(dialog) {
            window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return dialog
    }


    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.setCancelable(false)
            with(dialog) {
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tryAgainBtn: Button = view.findViewById(R.id.try_again)
        tryAgainBtn.setOnClickListener {
            dialog?.dismiss()
            Constant.isNoInternetPopShown = false
            val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
            startActivity(intent)
        }

        dialog?.setOnDismissListener {
            Constant.isNoInternetPopShown = false
        }
    }


    companion object {
        var NoInternetPopup: NoInternetPopup? = null

        @JvmStatic
        fun newInstance(): NoInternetPopup {
            NoInternetPopup = NoInternetPopup()
            return NoInternetPopup()
        }
    }

    fun showNoInternetPopup(
        requireActivity: Activity
    ) {
        try {
            if (!Constant.isNoInternetPopShown) {
                val fragment = newInstance()
                val fm = (requireActivity as FragmentActivity).supportFragmentManager
                val prev: Fragment? = fm.findFragmentByTag("PopUpClass")
                if (prev == null)
                    fragment.show(fm, "PopUpClass")
                Constant.isNoInternetPopShown = true
            }
        } catch (e: Exception) {

        }
    }
}