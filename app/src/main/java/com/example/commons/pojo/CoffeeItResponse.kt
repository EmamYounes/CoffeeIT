package com.example.commons.pojo

import com.google.gson.annotations.SerializedName

data class CoffeeItResponse(

    @field:SerializedName("types")
    val types: List<TypesItem?>? = null,

    @field:SerializedName("sizes")
    val sizes: List<SizesItem?>? = null,

    @field:SerializedName("extras")
    val extras: List<ExtrasItem?>? = null
)

data class SubselectionsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("_id")
    val id: String? = null
)

data class ExtrasItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("subselections")
    val subselections: List<SubselectionsItem?>? = null,

    @field:SerializedName("_id")
    val id: String? = null
)

data class TypesItem(

    @field:SerializedName("sizes")
    val sizes: List<String?>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("extras")
    val extras: List<String?>? = null,

    @field:SerializedName("_id")
    val id: String? = null
)

data class SizesItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("_id")
    val id: String? = null
)
