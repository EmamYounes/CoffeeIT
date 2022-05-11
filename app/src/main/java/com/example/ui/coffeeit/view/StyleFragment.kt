package com.example.ui.coffeeit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.pojo.CoffeeMachineResponse
import com.example.commons.pojo.TypesItem
import com.example.commons.ui.BaseFragment
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.adapter.StyleAdapter
import com.example.ui.coffeeit.data_classes.OverviewDataItem
import com.example.ui.coffeeit.data_classes.Type
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModel
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class StyleFragment : BaseFragment(), KodeinAware, OnClick {

    override val kodein by kodein()

    private val factory: CoffeeBrewViewModelFactory by instance()
    private val viewModel: CoffeeBrewViewModel by instance()

    private var recyclerview: RecyclerView? = null
    private var emptyCase: LinearLayout? = null

    private var styleAdapter = StyleAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.style_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.overviewList.accept(mutableListOf())
    }

    private fun init() {
        recyclerview = view?.findViewById(R.id.recyclerview)
        emptyCase = view?.findViewById(R.id.empty_case)
    }


    private fun bindUI() {
        styleAdapter.callbacks = this
        callCoffeeItApi()
        initRecyclerView()
        manageCoffeeItApiResponse()
        manageCoffeeItApiErrorResponse()
    }

    private fun callCoffeeItApi() {
        showLoading()
        viewModel.getCoffeeItApi()
    }

    private fun manageCoffeeItApiResponse() {
        viewModel.successGetCoffeeItApi.subscribe {
            hideLoading()
            if (it.types?.isEmpty() == true) {
                handleEmptyState()
            } else {
                handleListState(it)
            }
        }
    }

    private fun manageCoffeeItApiErrorResponse() {
        viewModel.errorGetCoffeeItApi.subscribe {
            hideLoading()
            showMessage(it)
        }
    }

    private fun handleListState(it: CoffeeMachineResponse) {
        it.types?.let { it1 -> styleAdapter.addList(it1) }
        recyclerview?.visibility = View.VISIBLE
        emptyCase?.visibility = View.GONE
    }

    private fun handleEmptyState() {
        recyclerview?.visibility = View.GONE
        emptyCase?.visibility = View.VISIBLE
    }

    private fun initRecyclerView() {
        recyclerview?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = styleAdapter
        }

    }


    override fun onItemClickedStyle(item: TypesItem) {

        item.let { viewModel.selectedStyle.accept(item) }
        val overviewDataItem = OverviewDataItem(item.name.toString(), Type.STYLE)
        val overviewList = mutableListOf(overviewDataItem)
        viewModel.overviewList.value?.let { overviewList.addAll(it) }
        overviewList.let { viewModel.overviewList.accept(it) }
        navToSizeFragment()
    }

    private fun navToSizeFragment() {
        val navController =
            activity?.findNavController(R.id.coffee_brew_nav_host_fragment)
        navController?.navigate(R.id.sizeFragment, bundleOf(), options)
    }

    override fun getSubTitle(): String {
        return getString(R.string.select_your_style)
    }

    override fun showBackBtn(): Boolean {
        return false
    }
}
