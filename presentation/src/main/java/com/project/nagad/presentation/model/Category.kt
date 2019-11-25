package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.CategoryEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject


data class Category(

    val id: Int,
    val image: String,
    val name: String
)


class CategoryDomainMapper @Inject constructor() : Mapper<CategoryEntity, Category> {

    override fun fromViewToDomain(e: Category): CategoryEntity {
        return CategoryEntity(
            id = e.id,
            image = e.image,
            name = e.name
        )
    }

    override fun toViewFromDomain(t: CategoryEntity): Category {
        return Category(
            id = t.id,
            image = t.image,
            name = t.name
        )
    }
}