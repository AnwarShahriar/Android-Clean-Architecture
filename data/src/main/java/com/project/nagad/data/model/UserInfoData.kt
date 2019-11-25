package com.project.nagad.data.model

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.domain.entities.UserInfoEntity
import javax.inject.Inject

data class UserInfoData(

    val userIdentifier: String,
    val userName: String,
    val userEmail: String,
    val userAddress: String,
    val userType: String,
    val premiumCustomer: Boolean,
    val userCouponBalance: Double,
    val isEligibleForUpgrade: Boolean
)

class UserInfoDataDomainMapper @Inject constructor() : Mapper<UserInfoEntity, UserInfoData> {
    override fun fromDataToDomain(e: UserInfoData): UserInfoEntity {
        return UserInfoEntity(
            userIdentifier = e.userIdentifier,
            userName = e.userName,
            userAddress = e.userAddress,
            premiumCustomer = e.premiumCustomer,
            userCouponBalance = e.userCouponBalance,
            userEmail = e.userEmail,
            userType = e.userType
        )
    }

    override fun toDataFromDomain(t: UserInfoEntity): UserInfoData {
        return UserInfoData(
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