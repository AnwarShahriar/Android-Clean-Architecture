package com.project.nagad.local.database.pref

/**
 * Created by ashif
 * Contains all the keys used in SharedPreference of application
 */
interface PrefConstants {
    companion object {
        const val PREFERENCE_NAME = "nagad_bazaar_app_pref"

        const val IS_USER_LOGGED_IN = "is_user_logged_in"
        const val IS_FIRST_TIME = "is_first_time"
    }
}