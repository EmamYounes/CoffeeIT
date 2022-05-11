package com.example.ui.coffeeit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.commons.pojo.ExtrasItem
import com.example.commons.ui.BaseFragment
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.adapter.ExtraExpandableListAdapter
import com.example.ui.coffeeit.data_classes.ExtraDataItem
import com.example.ui.coffeeit.data_classes.OverviewDataItem
import com.example.ui.coffeeit.data_classes.Type
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModel
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ExtraFragment : BaseFragment(), KodeinAware, OnClick {

    override val kodein by kodein()

    private val factory: CoffeeBrewViewModelFactory by instance()

    private lateinit var viewModel: CoffeeBrewViewModel
    private var recyclerview: ExpandableListView? = null
    private var emptyCase: LinearLayout? = null
    private var next: TextView? = null

    private var extraAdapter = ExtraExpandableListAdapter(
        requireContext(), mutableListOf(), hashMapOf()
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.extra_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        bindUI()
    }

    override fun onResume() {
        super.onResume()
        val list = viewModel.overviewList.value?.filter {
            (it.type.name != Type.EXTRA.name || it.type.name != Type.SUB_EXTRA.name)
        }
        list?.let { viewModel.overviewList.accept(it) }
    }

    private fun init() {
        extraAdapter.callbacks = this
        viewModel = ViewModelProviders.of(this, factory).get(CoffeeBrewViewModel::class.java)
        recyclerview = view?.findViewById(R.id.recyclerview)
        emptyCase = view?.findViewById(R.id.empty_case)
        next = view?.findViewById(R.id.next)

    }


    private fun bindUI() {
        manageSizeList()
        handleNextBtnAction()
    }

    private fun handleNextBtnAction() {
        next?.setOnClickListener {
            navToOverviewFragment()
        }
    }

    private fun manageSizeList() {
        val fullExtrasList = viewModel.successGetCoffeeItApi.value?.extras
        val selectedExtrasList = fullExtrasList?.filter {
            viewModel.selectedStyle.value?.extras?.contains(it?.id) == true
        }
        if (selectedExtrasList?.isEmpty() == true) {
            handleEmptyState()
        } else {
            selectedExtrasList?.let { handleListState(it) }
        }
    }

    private fun handleListState(extraList: List<ExtrasItem?>) {
        val extraNameList: List<String?> =
            extraList.map {
                it?.name
            }.toList()

        val expandableListDetail =
            extraList.map { extrasItem ->
                val data = HashMap<String, List<ExtraDataItem>>()
                val list = extrasItem?.subselections?.map {
                    ExtraDataItem(it?.name.toString(), false)
                }
                data[extrasItem?.name.toString()] = list as List<ExtraDataItem>
                data
            }.first()
        extraAdapter =
            ExtraExpandableListAdapter(
                requireContext(),
                extraNameList as ArrayList<String>,
                expandableListDetail
            )
        recyclerview!!.setAdapter(extraAdapter)
        recyclerview?.visibility = View.VISIBLE
        emptyCase?.visibility = View.GONE
    }

    private fun handleEmptyState() {
        recyclerview?.visibility = View.GONE
        emptyCase?.visibility = View.VISIBLE
    }

    override fun onItemClickedSubExtra(item: ExtraDataItem) {
        val overviewList = viewModel.overviewList.value
        val overviewDataItem = OverviewDataItem(item.name, Type.SUB_EXTRA)
        overviewList?.toMutableList()?.add(overviewDataItem)
        overviewList?.let { viewModel.overviewList.accept(it) }
    }

    private fun navToOverviewFragment() {
        val navController =
            activity?.findNavController(R.id.coffee_brew_nav_host_fragment)
        navController?.navigate(R.id.overviewFragment, bundleOf(), options)
    }

    override fun getSubTitle(): String {
        return getString(R.string.select_your_extra)
    }
}
