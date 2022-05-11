package com.example.ui.coffeeit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.data_classes.OverviewDataItem
import com.example.ui.coffeeit.data_classes.Type


class OverviewAdapter(myDataset: MutableList<OverviewDataItem>) :
    RecyclerView.Adapter<OverviewAdapter.OverviewViewHolder>() {

    private var mDataset: MutableList<OverviewDataItem>? = null


    private var viewHolder: OverviewViewHolder? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverviewViewHolder {
        // create a new view
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.overview_item, parent, false)

        return OverviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
        viewHolder = holder
        holder.bind(mDataset?.get(position)!!)
    }


    inner class OverviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var extraSubView: RelativeLayout = view.findViewById(R.id.extra_sub_view)
        var extraSubName: TextView = view.findViewById(R.id.extra_sub_name)

        fun bind(model: OverviewDataItem) {
            if (model.type.name == Type.EXTRA_AMOUNT.name) {
                extraSubView.visibility = View.VISIBLE
                name.visibility = View.GONE
                extraSubName.text = model.name
            } else {
                name.text = model.name
            }
        }
    }


    override fun getItemCount(): Int {
        return mDataset!!.size
    }

    fun addList(lst: List<OverviewDataItem?>) {
        mDataset = lst as MutableList<OverviewDataItem>
        notifyDataSetChanged()
    }

    fun getCurrentList(): List<OverviewDataItem> {
        return mDataset!!
    }

    init {
        mDataset = myDataset
    }

}
