package com.example.commons.ui

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ui.coffeeit.R

open class BaseActivity : AppCompatActivity() {

    private var backBtn: ImageView? = null
    private var title: TextView? = null
    private var subTitle: TextView? = null

    override fun setContentView(layoutResID: Int) {
        val constraintLayout: ConstraintLayout =
            layoutInflater.inflate(R.layout.base_activity, null) as ConstraintLayout
        val activityContainer: FrameLayout = constraintLayout.findViewById(R.id.layout_container)
        layoutInflater.inflate(layoutResID, activityContainer, true)

        super.setContentView(constraintLayout)
    }

    override fun onResume() {
        super.onResume()
        bindView()
    }

    private fun bindView() {
        initView()
        handleBackBtnAction()
    }

    private fun initView() {
        backBtn = findViewById(R.id.back_btn)
        title = findViewById(R.id.title)
        subTitle = findViewById(R.id.sub_title)
    }

    private fun handleBackBtnAction() {
        backBtn?.setOnClickListener {
            this.onBackPressed()
        }
    }

    fun setTitle(message: String) {
        title?.text = message
    }

    fun setSubTitle(message: String) {
        subTitle?.text = message
    }

    fun hideBackBtn() {
        backBtn?.visibility = View.GONE
    }

    fun showBackBtn() {
        backBtn?.visibility = View.VISIBLE
    }
}