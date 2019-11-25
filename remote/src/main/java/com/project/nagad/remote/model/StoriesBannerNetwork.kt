package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.StoriesBannerData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class StoriesBannerNetwork(

    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("web_link") val webLink: String,
    @SerializedName("deep_link") val deepLink: String
)

class StoriesBannerNetworkMapper @Inject constructor() :
    Mapper<StoriesBannerData, StoriesBannerNetwork> {
    override fun fromNetworkToData(e: StoriesBannerNetwork): StoriesBannerData {
        return StoriesBannerData(
            id = e.id,
            image = e.image,
            webLink = e.webLink,
            deepLink = e.deepLink
        )
    }

    override fun toNetworkFromData(t: StoriesBannerData): StoriesBannerNetwork {
        return StoriesBannerNetwork(
            id = t.id,
            image = t.image,
            webLink = t.webLink,
            deepLink = t.deepLink
        )
    }
}