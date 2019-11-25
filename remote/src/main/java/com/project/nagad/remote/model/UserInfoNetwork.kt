package com.project.nagad.remote.model

import com.google.gson.annotations.SerializedName
import com.project.nagad.data.model.UserInfoData
import com.project.nagad.remote.mapper.Mapper
import javax.inject.Inject

data class UserInfoNetwork(
    @SerializedName("user_id") val userIdentifier: String,
    @SerializedName("user_name") val userName: String,
    @SerializedName("user_email") val userEmail: String,
    @SerializedName("user_address") val userAddress: String,
    @SerializedName("user_type") val userType: String,
    @SerializedName("is_premium") val premiumCustomer: Boolean,
    @SerializedName("coupon_balance") val userCouponBalance: Double,
    @SerializedName("eligible_for_upgrade") val isEligibleForUpgrade: Boolean
)

class UserInfoNetworkMapper @Inject constructor() : Mapper<UserInfoData, UserInfoNetwork> {

    override fun fromNetworkToData(e: UserInfoNetwork): UserInfoData {
        return UserInfoData(
            userIdentifier = e.userIdentifier,
            userName = e.userName,
            userAddress = e.userAddress,
            premiumCustomer = e.premiumCustomer,
            userCouponBalance = e.userCouponBalance,
            userEmail = e.userEmail,
            userType = e.userType,
            isEligibleForUpgrade = e.isEligibleForUpgrade
        )
    }

    override fun toNetworkFromData(t: UserInfoData): UserInfoNetwork {
        return UserInfoNetwork(
            userIdentifier = t.userIdentifier,
            userName = t.userName,
            userAddress = t.userAddress,
            premiumCustomer = t.premiumCustomer,
            userCouponBalance = t.userCouponBalance,
            userEmail = t.userEmail,
            userType = t.userType,
            isEligibleForUpgrade = t.isEligibleForUpgrade
        )
    }
}