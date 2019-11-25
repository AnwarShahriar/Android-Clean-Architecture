package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.StoriesBannerEntity
import javax.inject.Inject

data class StoriesBannerData(

    val id: Int,
    val image: String,
    val webLink: String,
    val deepLink: String
)

class StoriesBannerDataDomainMapper @Inject constructor() :
    Mapper<StoriesBannerEntity, StoriesBannerData> {
    override fun fromDataToDomain(e: StoriesBannerData): StoriesBannerEntity {
        return StoriesBannerEntity(
            id = e.id,
            image = e.image,
            webLink = e.webLink,
            deepLink = e.deepLink
        )
    }

    override fun toDataFromDomain(t: StoriesBannerEntity): StoriesBannerData {
        return StoriesBannerData(
            id = t.id,
            image = t.image,
            webLink = t.webLink,
            deepLink = t.deepLink
        )
    }
}