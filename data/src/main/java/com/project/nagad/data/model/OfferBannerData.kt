package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.OfferBannerEntity
import javax.inject.Inject

data class OfferBannerData (

    val id : Int,
    val image : String,
    val webLink : String,
    val deepLink : String
)

class OfferBannerDataDomainMapper @Inject constructor() : Mapper<OfferBannerEntity, OfferBannerData> {
    override fun fromDataToDomain(e: OfferBannerData): OfferBannerEntity {
        return OfferBannerEntity(
            id = e.id,
            image = e.image,
            webLink = e.webLink,
            deepLink = e.deepLink
        )
    }

    override fun toDataFromDomain(t: OfferBannerEntity): OfferBannerData {
        return OfferBannerData(
            id = t.id,
            image = t.image,
            webLink = t.webLink,
            deepLink = t.deepLink
        )
    }
}