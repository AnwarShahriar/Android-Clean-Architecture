package com.project.nagad.domain.entities


data class ProductsEntity (

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