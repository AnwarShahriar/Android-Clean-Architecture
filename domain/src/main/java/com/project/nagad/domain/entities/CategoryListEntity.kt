package com.project.nagad.domain.entities

data class CategoryListEntity(

    val type: String,
    val headerName: String,
    val categoryList: List<CategoryEntity>
)