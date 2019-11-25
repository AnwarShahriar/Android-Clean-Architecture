package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.OfferBannerEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject

data class OfferBanner (

    val id : Int,
    val image : String,
    val webLink : String,
    val deepLink : String
)

class OfferBannerDomainMapper @Inject constructor() :
    Mapper<OfferBannerEntity, OfferBanner> {
    override fun fromViewToDomain(e: OfferBanner): OfferBannerEntity {
        return OfferBannerEntity(
            id = e.id,
            image = e.image,
            webLink = e.webLink,
            deepLink = e.deepLink
        )
    }

    override fun toViewFromDomain(t: OfferBannerEntity): OfferBanner {
        return OfferBanner(
            id = t.id,
            image = t.image,
            webLink = t.webLink,
            deepLink = t.deepLink
        )
    }
}