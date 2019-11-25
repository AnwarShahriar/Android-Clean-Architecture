package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.ProductsEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject


data class Products(

    val productId: Int,
    val vendorName: String,
    val image: String,
    val productTitle: String,
    val webLink: String,
    val deepLink: String,
    val price: Int,
    val previousPrice: Int,
    val discountPercent: Int,
    val ratingValue: Double,
    val totalRating: Int,
    val location: String
)


class ProductsDomainMapper @Inject constructor() : Mapper<ProductsEntity, Products> {

    override fun fromViewToDomain(e: Products): ProductsEntity {
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

    override fun toViewFromDomain(t: ProductsEntity): Products {
        return Products(
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