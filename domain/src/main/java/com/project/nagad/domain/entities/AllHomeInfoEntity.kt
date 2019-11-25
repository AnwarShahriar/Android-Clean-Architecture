package com.project.nagad.domain.entities


data class AllHomeInfoEntity(

    val storiesBannerList: List<StoriesBannerEntity>,
    val offerBannerList: List<OfferBannerEntity>,
    val categoryList: CategoryListEntity,
    val hotProducts: HotProductsEntity
)