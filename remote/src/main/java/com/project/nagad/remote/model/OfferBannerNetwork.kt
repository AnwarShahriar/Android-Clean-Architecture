package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.OfferBannerData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class OfferBannerNetwork(

    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("web_link") val webLink: String,
    @SerializedName("deep_link") val deepLink: String
)

class OfferBannerNetworkMapper @Inject constructor() : Mapper<OfferBannerData, OfferBannerNetwork> {

    override fun fromNetworkToData(e: OfferBannerNetwork): OfferBannerData {
        return OfferBannerData(
            id = e.id,
            image = e.image,
            webLink = e.webLink,
            deepLink = e.deepLink
        )
    }

    override fun toNetworkFromData(t: OfferBannerData): OfferBannerNetwork {

        return OfferBannerNetwork(
            id = t.id,
            image = t.image,
            webLink = t.webLink,
            deepLink = t.deepLink
        )
    }
}