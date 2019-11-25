package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.StoriesBannerEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject

data class StoriesBanner(

    val id: Int,
    val image: String,
    val webLink: String,
    val deepLink: String
)

class StoriesBannerDomainMapper @Inject constructor() :
    Mapper<StoriesBannerEntity, StoriesBanner> {
    override fun fromViewToDomain(e: StoriesBanner): StoriesBannerEntity {
        return StoriesBannerEntity(
            id = e.id,
            image = e.image,
            webLink = e.webLink,
            deepLink = e.deepLink
        )
    }

    override fun toViewFromDomain(t: StoriesBannerEntity): StoriesBanner {
        return StoriesBanner(
            id = t.id,
            image = t.image,
            webLink = t.webLink,
            deepLink = t.deepLink
        )
    }
}