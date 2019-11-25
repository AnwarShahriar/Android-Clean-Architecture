package com.project.nagad.domain.entities

data class HotProductsEntity(

    val type: String,
    val headerName: String,
    val productList: List<ProductsEntity>
)