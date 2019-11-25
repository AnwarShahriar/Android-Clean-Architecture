package com.project.nagad.presentation.utils

import com.project.nagad.presentation.model.UserInfo

class TestDataGenerator {

    companion object {
        fun generateUserInfo(): UserInfo {
            return UserInfo(
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

        fun generateUpgradableUserInfo(): UserInfo {
            return UserInfo(
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