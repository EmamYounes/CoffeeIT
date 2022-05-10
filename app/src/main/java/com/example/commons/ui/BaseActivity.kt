package com.example.commons.ui

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ui.coffeeit.R

open class BaseActivity : AppCompatActivity() {

    private var backBtn: ImageView? = null
    private var title: TextView? = null
    private var subTitle: TextView? = null
    private var toolBar: LinearLayout? = null

    override fun setContentView(layoutResID: Int) {
        val linearLayout: LinearLayout =
            layoutInflater.inflate(R.layout.base_activity, null) as LinearLayout
        val activityContainer: FrameLayout = linearLayout.findViewById(R.id.layout_container)
        layoutInflater.inflate(layoutResID, activityContainer, true)

        super.setContentView(linearLayout)
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
        toolBar = findViewById(R.id.tool_bar)
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

    fun hideToolBar() {
        toolBar?.visibility = View.GONE
    }

    fun showToolBar() {
        toolBar?.visibility = View.VISIBLE
    }
}