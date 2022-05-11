package com.example.ui.coffeeit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import com.example.ui.coffeeit.R
import com.example.ui.coffeeit.data_classes.ExtraDataItem
import com.example.ui.coffeeit.view.OnClick

class ExtraExpandableListAdapter internal constructor(
    private val context: Context,
    private val titleList: List<String>,
    private val dataList: HashMap<String, List<ExtraDataItem>>
) : BaseExpandableListAdapter() {

    var callbacks: OnClick? = null

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val extraDataItem = getChild(listPosition, expandedListPosition) as ExtraDataItem
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.extra_sub_item, parent, false)
        }
        convertView?.setPadding(64)
        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.extra_sub_name)
        val checkImage = convertView.findViewById<ImageView>(R.id.check_image)

        checkImage.tag = expandedListPosition

        expandedListTextView.text = extraDataItem.name
        if (extraDataItem.isChecked) {
            checkImage.visibility = View.VISIBLE
        } else {
            checkImage.visibility = View.GONE
        }

        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList[this.titleList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return this.titleList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.extra_item, parent, false)
        }
        if (!isExpanded) {
            convertView?.setPadding(16)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.type_name)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    fun updateItem(extraName: String, list: List<ExtraDataItem>) {

        dataList[extraName] = list
        notifyDataSetChanged()
    } //for add new item

}