package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.HotProductsEntity
import javax.inject.Inject

data class HotProductsData(

    val type: String,
    val headerName: String,
    val productList: List<ProductsData>
)

class HotProductsDataDomainMapper @Inject constructor() :
    Mapper<HotProductsEntity, HotProductsData> {
    override fun fromDataToDomain(e: HotProductsData): HotProductsEntity {
        return HotProductsEntity(
            type = e.type,
            headerName = e.headerName,
            productList = e.productList.map { ProductDataDomainMapper().fromDataToDomain(it) }
        )
    }

    override fun toDataFromDomain(t: HotProductsEntity): HotProductsData {
        return HotProductsData(
            type = t.type,
            headerName = t.headerName,
            productList = t.productList.map { ProductDataDomainMapper().toDataFromDomain(it) }
        )
    }
}