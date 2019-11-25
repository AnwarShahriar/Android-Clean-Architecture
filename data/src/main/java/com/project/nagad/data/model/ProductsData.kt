package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.ProductsEntity
import javax.inject.Inject


data class ProductsData (

    val productId : Int,
    val vendorName : String,
    val image : String,
    val productTitle : String,
    val webLink : String,
    val deepLink : String,
    val price : Int,
    val previousPrice : Int,
    val discountPercent : Int,
    val ratingValue : Double,
    val totalRating : Int,
    val location : String
)


class ProductDataDomainMapper @Inject constructor() : Mapper<ProductsEntity,ProductsData > {

    override fun fromDataToDomain(e: ProductsData): ProductsEntity {
        return ProductsEntity(
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

    override fun toDataFromDomain(t: ProductsEntity): ProductsData {
        return ProductsData(
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