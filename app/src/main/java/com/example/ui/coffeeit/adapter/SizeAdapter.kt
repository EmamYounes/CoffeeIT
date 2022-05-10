package com.example.ui.coffeeit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.pojo.SizesItem
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.view.OnClick


class SizeAdapter(myDataset: MutableList<SizesItem>) :
    RecyclerView.Adapter<SizeAdapter.StyleViewHolder>() {

    private var mDataset: MutableList<SizesItem>? = null
    var callbacks: OnClick? = null


    private var viewHolder: StyleViewHolder? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StyleViewHolder {
        // create a new view
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.style_item, parent, false)

        return StyleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StyleViewHolder, position: Int) {
        viewHolder = holder
        holder.bind(mDataset?.get(position)!!, position)
    }


    inner class StyleViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var typeView: RelativeLayout = view.findViewById(R.id.type_view)
        var typeName: TextView = view.findViewById(R.id.type_name)

        fun bind(model: SizesItem, position: Int) {
            typeName.text = model.name
            typeView.tag = position
            typeName.tag = position
            typeView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            val model = mDataset?.get(p0?.tag as Int)
            model?.let { callbacks!!.onItemClickedSize(it) }
        }
    }


    override fun getItemCount(): Int {
        return mDataset!!.size
    }

    fun addList(lst: List<SizesItem?>) {
        mDataset = lst as MutableList<SizesItem>
        notifyDataSetChanged()
    }

    fun getCurrentList(): List<SizesItem> {
        return mDataset!!
    }

    init {
        mDataset = myDataset
    }

}
