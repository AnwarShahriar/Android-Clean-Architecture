package com.project.nagad.presentation.model

import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.presentation.mapper.Mapper
import javax.inject.Inject

data class UserInfo(

    val userIdentifier: String,
    val userName: String,
    val userEmail: String,
    val userAddress: String,
    val userType: String,
    val premiumCustomer: Boolean,
    val userCouponBalance: Double,
    val isEligibleForUpgrade: Boolean
)

class UserInfoDomainMapper @Inject constructor() : Mapper<UserInfoEntity, UserInfo> {
    override fun fromViewToDomain(e: UserInfo): UserInfoEntity {
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

    override fun toViewFromDomain(t: UserInfoEntity): UserInfo {
        return UserInfo(
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