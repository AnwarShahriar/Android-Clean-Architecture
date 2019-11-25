package com.project.nagad.local.utils

import com.project.nagad.local.model.UserInfoLocal

class TestDataGenerator {
    companion object {
        fun generateUserInfo(): UserInfoLocal {
            return UserInfoLocal(
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
    }
}