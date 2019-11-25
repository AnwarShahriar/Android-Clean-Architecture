package com.project.nagad.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.nagad.data.model.UserInfoData
import com.project.nagad.local.mapper.Mapper
import javax.inject.Inject

@Entity(tableName = "user_info")
data class UserInfoLocal(
    @PrimaryKey @ColumnInfo(name = "user_id") val userIdentifier: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "user_email") val userEmail: String,
    @ColumnInfo(name = "user_address") val userAddress: String,
    @ColumnInfo(name = "user_type") val userType: String,
    @ColumnInfo(name = "is_premium") val premiumCustomer: Boolean,
    @ColumnInfo(name = "coupon_balance") val userCouponBalance: Double,
    @ColumnInfo(name = "eligible_for_upgrade") val isEligibleForUpgrade: Boolean
)

class UserInfoLocalMapper @Inject constructor() : Mapper<UserInfoData,UserInfoLocal> {
    override fun fromLocalToData(e: UserInfoLocal): UserInfoData {
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

    override fun toLocalFromData(t: UserInfoData): UserInfoLocal {
        return UserInfoLocal(
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