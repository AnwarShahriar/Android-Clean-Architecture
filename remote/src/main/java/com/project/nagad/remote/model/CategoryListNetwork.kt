package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.CategoryListData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class CategoryListNetwork(

    @SerializedName("type") val type: String,
    @SerializedName("header_name") val headerName: String,
    @SerializedName("category_list") val categoryList: List<CategoryNetwork>
)

class CategoryListNetworkMapper @Inject constructor() :
    Mapper<CategoryListData, CategoryListNetwork> {

    override fun fromNetworkToData(e: CategoryListNetwork): CategoryListData {
        return CategoryListData(
            type = e.type,
            headerName = e.headerName,
            categoryList = e.categoryList.map { CategoryNetworkMapper().fromNetworkToData(it) }
        )
    }

    override fun toNetworkFromData(t: CategoryListData): CategoryListNetwork {

        return CategoryListNetwork(
            type = t.type,
            headerName = t.headerName,
            categoryList = t.categoryList.map { CategoryNetworkMapper().toNetworkFromData(it) }
        )
    }
}