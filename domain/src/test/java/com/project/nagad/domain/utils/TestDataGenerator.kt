package com.project.nagad.domain.utils

import com.project.nagad.domain.entities.UserInfoEntity

class TestDataGenerator {

    companion object {
        fun generateUserInfo(): UserInfoEntity {
            return UserInfoEntity(
                "1BFC9A38E6C7",
                "John Doe",
                "john@gmail.com",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "Regular",
                false,
                4579.75
            )
        }

        fun generateUpgradableUserInfo(): UserInfoEntity {
            return UserInfoEntity(
                "1BFC9A38E6C7",
                "Agent Smith",
                "smith@gmail.com",
                "307, Palm drive, Virdigris Square, CA - 95014",
                "Regular",
                false,
                50579.75
            )
        }
    }
}