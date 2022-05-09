package com.example.ui.coffeeit.view

import com.example.commons.pojo.ExtrasItem
import com.example.commons.pojo.SizesItem
import com.example.commons.pojo.TypesItem
import com.example.ui.coffeeit.data_classes.ExtraDataItem

interface OnClick {
    fun onItemClickedStyle(item: TypesItem) {}
    fun onItemClickedSize(item: SizesItem) {}
    fun onItemClickedExtra(item: ExtrasItem) {}
    fun onItemClickedSubExtra(item: ExtraDataItem) {}
}