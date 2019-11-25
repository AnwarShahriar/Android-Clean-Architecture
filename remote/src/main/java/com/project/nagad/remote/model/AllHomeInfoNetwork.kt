package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.AllHomeInfoData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class AllHomeInfoNetwork(

    @SerializedName("success_stories") val storiesBannerList: List<StoriesBannerNetwork>,
    @SerializedName("offer_banners") val offerBannerList: List<OfferBannerNetwork>,
    @SerializedName("categories") val categoryList: CategoryListNetwork,
    @SerializedName("hot_products") val hotProducts: HotProductsNetwork
)

class AllHomeInfoNetworkMapper @Inject constructor() : Mapper<AllHomeInfoData, AllHomeInfoNetwork> {

    override fun fromNetworkToData(e: AllHomeInfoNetwork): AllHomeInfoData {
        return AllHomeInfoData(
            storiesBannerList = e.storiesBannerList.map {
                StoriesBannerNetworkMapper().fromNetworkToData(
                    it
                )
            },
            offerBannerList = e.offerBannerList.map {
                OfferBannerNetworkMapper().fromNetworkToData(
                    it
                )
            },
            categoryList = CategoryListNetworkMapper().fromNetworkToData(e.categoryList),
            hotProducts = HotProductsNetworkMapper().fromNetworkToData(e.hotProducts)
        )
    }

    override fun toNetworkFromData(t: AllHomeInfoData): AllHomeInfoNetwork {

        return AllHomeInfoNetwork(
            storiesBannerList = t.storiesBannerList.map {
                StoriesBannerNetworkMapper().toNetworkFromData(
                    it
                )
            },
            offerBannerList = t.offerBannerList.map {
                OfferBannerNetworkMapper().toNetworkFromData(
                    it
                )
            },
            categoryList = CategoryListNetworkMapper().toNetworkFromData(t.categoryList),
            hotProducts = HotProductsNetworkMapper().toNetworkFromData(t.hotProducts)
        )
    }
}