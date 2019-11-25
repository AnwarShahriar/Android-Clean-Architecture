package com.project.nagad.domain.entities

data class UserInfoEntity(

    val userIdentifier: String,
    val userName: String,
    val userEmail: String,
    val userAddress: String,
    val userType: String,
    val premiumCustomer: Boolean,
    val userCouponBalance: Double

) {
    companion object {
        private const val ACCOUNT_COUPON_BALANCE = 30000 // $30000
    }

    val isEligibleForUpgrade: Boolean
        get() = userCouponBalance >= ACCOUNT_COUPON_BALANCE

}