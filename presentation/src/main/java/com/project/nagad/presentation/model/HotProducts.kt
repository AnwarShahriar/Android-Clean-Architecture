package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.HotProductsEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject

data class HotProducts(

    val type: String,
    val headerName: String,
    val productList: List<Products>
)

class HotProductsDomainMapper @Inject constructor() :
    Mapper<HotProductsEntity, HotProducts> {
    override fun fromViewToDomain(e: HotProducts): HotProductsEntity {
        return HotProductsEntity(
            type = e.type,
            headerName = e.headerName,
            productList = e.productList.map { ProductsDomainMapper().fromViewToDomain(it) }
        )
    }

    override fun toViewFromDomain(t: HotProductsEntity): HotProducts {
        return HotProducts(
            type = t.type,
            headerName = t.headerName,
            productList = t.productList.map { ProductsDomainMapper().toViewFromDomain(it) }
        )
    }
}