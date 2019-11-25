package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.CategoryListEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject

data class CategoryList(

    val type: String,
    val headerName: String,
    val categoryList: List<Category>
)

class CategoryListDomainMapper @Inject constructor() :
    Mapper<CategoryListEntity, CategoryList> {
    override fun fromViewToDomain(e: CategoryList): CategoryListEntity {
        return CategoryListEntity(
            type = e.type,
            headerName = e.headerName,
            categoryList = e.categoryList.map { CategoryDomainMapper().fromViewToDomain(it) }
        )
    }

    override fun toViewFromDomain(t: CategoryListEntity): CategoryList {
        return CategoryList(
            type = t.type,
            headerName = t.headerName,
            categoryList = t.categoryList.map { CategoryDomainMapper().toViewFromDomain(it) }
        )
    }
}