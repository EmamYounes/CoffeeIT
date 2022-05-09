package com.example.ui.coffeeit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.pojo.SizesItem
import com.example.commons.ui.BaseFragment
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.adapter.SizeAdapter
import com.example.ui.coffeeit.data_classes.OverviewDataItem
import com.example.ui.coffeeit.data_classes.Type
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModel
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SizeFragment : BaseFragment(), KodeinAware, OnClick {

    override val kodein by kodein()

    private val factory: CoffeeBrewViewModelFactory by instance()

    private lateinit var viewModel: CoffeeBrewViewModel
    private var recyclerview: RecyclerView? = null
    private var emptyCase: LinearLayout? = null

    private var sizeAdapter = SizeAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.size_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindUI()
    }

    override fun onResume() {
        super.onResume()
        val list = viewModel.overviewList.value?.filter {
            it.type.name != Type.SIZE.name
        }
        list?.let { viewModel.overviewList.accept(it) }
    }

    private fun init() {
        sizeAdapter.callbacks = this
        viewModel = ViewModelProviders.of(this, factory).get(CoffeeBrewViewModel::class.java)
        recyclerview = view?.findViewById(R.id.recyclerview)
        emptyCase = view?.findViewById(R.id.empty_case)
    }


    private fun bindUI() {
        initRecyclerView()
        manageSizeList()
    }

    private fun manageSizeList() {
        val sizeList = viewModel.successGetCoffeeItApi.value?.sizes
        if (sizeList?.isEmpty() == true) {
            handleEmptyState()
        } else {
            sizeList?.let { handleListState(it) }
        }
    }

    private fun handleListState(sizeList: List<SizesItem?>) {
        sizeAdapter.addList(sizeList)
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
            adapter = sizeAdapter
        }

    }


    override fun onItemClickedSize(item: SizesItem) {
        val overviewList = viewModel.overviewList.value
        val overviewDataItem = OverviewDataItem(item.name.toString(), Type.SIZE)
        overviewList?.toMutableList()?.add(overviewDataItem)
        overviewList?.let { viewModel.overviewList.accept(it) }
        navToExtraFragment()
    }

    private fun navToExtraFragment() {
        val navController =
            activity?.findNavController(R.id.coffee_brew_nav_host_fragment)
        navController?.navigate(R.id.extraFragment, bundleOf(), options)
    }

    override fun getSubTitle(): String {
        return getString(R.string.select_your_size)
    }
}
