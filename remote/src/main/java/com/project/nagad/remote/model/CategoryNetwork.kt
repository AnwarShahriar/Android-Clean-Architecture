package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.CategoryData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class CategoryNetwork(

    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String
)

class CategoryNetworkMapper @Inject constructor() : Mapper<CategoryData, CategoryNetwork> {
    override fun fromNetworkToData(e: CategoryNetwork): CategoryData {
        return CategoryData(
            id = e.id,
            image = e.image,
            name = e.name
        )
    }

    override fun toNetworkFromData(t: CategoryData): CategoryNetwork {
        return CategoryNetwork(
            id = t.id,
            image = t.image,
            name = t.name
        )
    }
}