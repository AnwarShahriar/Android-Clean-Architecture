package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.AllHomeInfoEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject


data class AllHomeInfo(

    val storiesBannerList: List<StoriesBanner>,
    val offerBannerList: List<OfferBanner>,
    val categoryList: CategoryList,
    val hotProducts: HotProducts
)

class AllHomeInfoDomainMapper @Inject constructor() :
    Mapper<AllHomeInfoEntity, AllHomeInfo> {

    override fun fromViewToDomain(e: AllHomeInfo): AllHomeInfoEntity {
        return AllHomeInfoEntity(
            storiesBannerList = e.storiesBannerList.map {
                StoriesBannerDomainMapper().fromViewToDomain(
                    it
                )
            },
            offerBannerList = e.offerBannerList.map {
                OfferBannerDomainMapper().fromViewToDomain(
                    it
                )
            },
            categoryList = CategoryListDomainMapper().fromViewToDomain(e.categoryList),
            hotProducts = HotProductsDomainMapper().fromViewToDomain(e.hotProducts)
        )
    }

    override fun toViewFromDomain(t: AllHomeInfoEntity): AllHomeInfo {
        return AllHomeInfo(
            storiesBannerList = t.storiesBannerList.map {
                StoriesBannerDomainMapper().toViewFromDomain(
                    it
                )
            },
            offerBannerList = t.offerBannerList.map {
                OfferBannerDomainMapper().toViewFromDomain(
                    it
                )
            },
            categoryList = CategoryListDomainMapper().toViewFromDomain(t.categoryList),
            hotProducts = HotProductsDomainMapper().toViewFromDomain(t.hotProducts)
        )
    }
}