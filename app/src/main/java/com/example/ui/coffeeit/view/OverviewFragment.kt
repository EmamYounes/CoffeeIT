package com.example.ui.coffeeit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.ui.BaseFragment
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.adapter.OverviewAdapter
import com.example.ui.coffeeit.data_classes.OverviewDataItem
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModel
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class OverviewFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: CoffeeBrewViewModelFactory by instance()

    private lateinit var viewModel: CoffeeBrewViewModel

    private var recyclerview: RecyclerView? = null
    private var overviewAdapter = OverviewAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindUI()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, factory).get(CoffeeBrewViewModel::class.java)
        recyclerview = view?.findViewById(R.id.recyclerview)
    }

    private fun bindUI() {
        initRecyclerView()
        manageOverviewList()
    }

    private fun manageOverviewList() {
        val overviewList = viewModel.overviewList.value
        overviewList?.let { handleListState(it) }
    }

    private fun handleListState(sizeList: List<OverviewDataItem?>) {
        overviewAdapter.addList(sizeList)
    }

    private fun initRecyclerView() {
        recyclerview?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = overviewAdapter
        }

    }
}