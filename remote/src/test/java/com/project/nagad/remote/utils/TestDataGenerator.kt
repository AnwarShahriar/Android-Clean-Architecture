package com.project.nagad.remote.utils

import com.project.nagad.data.model.UserInfoData
import com.project.nagad.remote.model.UserInfoNetwork


class TestDataGenerator {

    companion object {
        fun generateUserInfoNetwork(): UserInfoNetwork {
            return UserInfoNetwork(
                "1BFC9A38E6C7",
                "John Doe",
                "john@gmail.com",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "Regular",
                false,
                4579.75,
                false
            )
        }

        fun generateUserInfoData(): UserInfoData {
            return UserInfoData(
                "1BFC9A38E6C7",
                "John Doe",
                "john@gmail.com",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "Regular",
                false,
                4579.75,
                false
            )
        }

        fun generateUpgradableUserInfo(): UserInfoNetwork {
            return UserInfoNetwork(
                "1BFC9A38E6C7",
                "Agent Smith",
                "smith@gmail.com",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "Regular",
                false,
                50579.75,
                true
            )
        }
    }
}