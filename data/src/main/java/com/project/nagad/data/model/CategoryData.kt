package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.CategoryEntity
import javax.inject.Inject


data class CategoryData(

    val id: Int,
    val image: String,
    val name: String
)


class CategoryDataDomainMapper @Inject constructor() : Mapper<CategoryEntity, CategoryData> {

    override fun fromDataToDomain(e: CategoryData): CategoryEntity {
        return CategoryEntity(
            id = e.id,
            image = e.image,
            name = e.name
        )
    }

    override fun toDataFromDomain(t: CategoryEntity): CategoryData {

        return CategoryData(
            id = t.id,
            image = t.image,
            name = t.name
        )
    }
}