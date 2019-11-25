package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.CategoryListEntity
import javax.inject.Inject

data class CategoryListData(

    val type: String,
    val headerName: String,
    val categoryList: List<CategoryData>
)

class CategoryListDataDomainMapper @Inject constructor() :
    Mapper<CategoryListEntity, CategoryListData> {
    override fun fromDataToDomain(e: CategoryListData): CategoryListEntity {
        return CategoryListEntity(
            type = e.type,
            headerName = e.headerName,
            categoryList = e.categoryList.map { CategoryDataDomainMapper().fromDataToDomain(it) }
        )
    }

    override fun toDataFromDomain(t: CategoryListEntity): CategoryListData {
        return CategoryListData(
            type = t.type,
            headerName = t.headerName,
            categoryList = t.categoryList.map { CategoryDataDomainMapper().toDataFromDomain(it) }
        )
    }
}