package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.ProductsData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class ProductsNetwork(

    @SerializedName("product_id") val productId: Int,
    @SerializedName("vendor_name") val vendorName: String,
    @SerializedName("image") val image: String,
    @SerializedName("product_title") val productTitle: String,
    @SerializedName("web_link") val webLink: String,
    @SerializedName("deep_link") val deepLink: String,
    @SerializedName("price") val price: Int,
    @SerializedName("previous_price") val previousPrice: Int,
    @SerializedName("discount_percent") val discountPercent: Int,
    @SerializedName("rating_value") val ratingValue: Double,
    @SerializedName("total_rating") val totalRating: Int,
    @SerializedName("location") val location: String
)

class ProductsNetworkMapper @Inject constructor() : Mapper<ProductsData, ProductsNetwork> {

    override fun fromNetworkToData(e: ProductsNetwork): ProductsData {
        return ProductsData(
            productId = e.productId,
            vendorName = e.vendorName,
            image = e.image,
            productTitle = e.productTitle,
            webLink = e.webLink,
            deepLink = e.deepLink,
            price = e.price,
            previousPrice = e.previousPrice,
            discountPercent = e.discountPercent,
            ratingValue = e.ratingValue,
            totalRating = e.totalRating,
            location = e.location
        )
    }

    override fun toNetworkFromData(t: ProductsData): ProductsNetwork {
        return ProductsNetwork(
            productId = t.productId,
            vendorName = t.vendorName,
            image = t.image,
            productTitle = t.productTitle,
            webLink = t.webLink,
            deepLink = t.deepLink,
            price = t.price,
            previousPrice = t.previousPrice,
            discountPercent = t.discountPercent,
            ratingValue = t.ratingValue,
            totalRating = t.totalRating,
            location = t.location
        )
    }
}