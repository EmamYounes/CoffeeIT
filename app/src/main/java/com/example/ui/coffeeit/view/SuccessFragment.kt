package com.example.ui.coffeeit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.commons.ui.BaseFragment
import com.example.ui.coffeeit.R

class SuccessFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.success_fragment, container, false)
    }
}