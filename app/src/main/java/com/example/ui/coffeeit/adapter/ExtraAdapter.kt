package com.example.ui.coffeeit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.pojo.ExtrasItem
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.view.OnClick


class ExtraAdapter(myDataset: MutableList<ExtrasItem>) :
    RecyclerView.Adapter<ExtraAdapter.StyleViewHolder>() {

    private var mDataset: MutableList<ExtrasItem>? = null
    var callbacks: OnClick? = null


    private var viewHolder: StyleViewHolder? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StyleViewHolder {
        // create a new view
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.extra_item, parent, false)

        return StyleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StyleViewHolder, position: Int) {
        viewHolder = holder
        holder.bind(mDataset?.get(position)!!, position)
    }


    inner class StyleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var typeView: RelativeLayout = view.findViewById(R.id.type_view)
        var typeName: TextView = view.findViewById(R.id.type_name)

        fun bind(model: ExtrasItem, position: Int) {
            typeName.text = model.name
            typeView.tag = position
            typeName.tag = position
            typeName.setOnClickListener {
                callbacks!!.onItemClickedExtra(model)
            }
        }
    }


    override fun getItemCount(): Int {
        return mDataset!!.size
    }

    fun addList(lst: List<ExtrasItem?>) {
        mDataset = lst as MutableList<ExtrasItem>
        notifyDataSetChanged()
    }

    fun getCurrentList(): List<ExtrasItem> {
        return mDataset!!
    }

    init {
        mDataset = myDataset
    }

}
