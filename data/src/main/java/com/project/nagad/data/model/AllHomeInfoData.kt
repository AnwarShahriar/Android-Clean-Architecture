package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.AllHomeInfoEntity
import javax.inject.Inject


data class AllHomeInfoData(

    val storiesBannerList: List<StoriesBannerData>,
    val offerBannerList: List<OfferBannerData>,
    val categoryList: CategoryListData,
    val hotProducts: HotProductsData
)

class AllHomeInfoDataDomainMapper @Inject constructor() :
    Mapper<AllHomeInfoEntity, AllHomeInfoData> {

    override fun fromDataToDomain(e: AllHomeInfoData): AllHomeInfoEntity {
        return AllHomeInfoEntity(
            storiesBannerList = e.storiesBannerList.map {
                StoriesBannerDataDomainMapper().fromDataToDomain(
                    it
                )
            },
            offerBannerList = e.offerBannerList.map {
                OfferBannerDataDomainMapper().fromDataToDomain(
                    it
                )
            },
            categoryList = CategoryListDataDomainMapper().fromDataToDomain(e.categoryList),
            hotProducts = HotProductsDataDomainMapper().fromDataToDomain(e.hotProducts)
        )
    }

    override fun toDataFromDomain(t: AllHomeInfoEntity): AllHomeInfoData {
        return AllHomeInfoData(
            storiesBannerList = t.storiesBannerList.map {
                StoriesBannerDataDomainMapper().toDataFromDomain(
                    it
                )
            },
            offerBannerList = t.offerBannerList.map {
                OfferBannerDataDomainMapper().toDataFromDomain(
                    it
                )
            },
            categoryList = CategoryListDataDomainMapper().toDataFromDomain(t.categoryList),
            hotProducts = HotProductsDataDomainMapper().toDataFromDomain(t.hotProducts)
        )
    }
}