package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.HotProductsData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class HotProductsNetwork(

    @SerializedName("type") val type: String,
    @SerializedName("header_name") val headerName: String,
    @SerializedName("all_products") val productList: List<ProductsNetwork>
)

class HotProductsNetworkMapper @Inject constructor() : Mapper<HotProductsData, HotProductsNetwork> {

    override fun fromNetworkToData(e: HotProductsNetwork): HotProductsData {
        return HotProductsData(
            type = e.type,
            headerName = e.headerName,
            productList = e.productList.map { ProductsNetworkMapper().fromNetworkToData(it) }
        )
    }

    override fun toNetworkFromData(t: HotProductsData): HotProductsNetwork {
        return HotProductsNetwork(
            type = t.type,
            headerName = t.headerName,
            productList = t.productList.map { ProductsNetworkMapper().toNetworkFromData(it) }
        )
    }
}