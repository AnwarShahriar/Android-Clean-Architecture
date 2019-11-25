package com.project.nagad.local.database.pref

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class AppPrefs @Inject constructor(
    private val context: Context
) {

    //get shared pref
    private val sharePreferences: SharedPreferences =
        context.getSharedPreferences(PrefConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    /**--- Read Preferences
     * @param prefKey = getting value by this key----- */

    fun getPref(prefKey: String): String? = sharePreferences.getString(prefKey, "")

    fun getIntPref(prefKey: String): Int = sharePreferences.getInt(prefKey, -1)

    fun getBooleanPref(prefKey: String): Boolean = sharePreferences.getBoolean(prefKey, false)


    /** ------ Write Shared Preferences
     * @param key = write/update value by this key */

    fun putPref(key: String, value: String) {
        val editor = sharePreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun putPref(key: String, value: Int) {
        val editor = sharePreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun putPref(key: String, value: Boolean) {
        val editor = sharePreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()

    }

    /**--- Clear All Preferences ---*/
    fun clearAllPref() = sharePreferences.edit().clear().apply()


    /**--- Clear single preferences ---
     * @param prefKey = clear preference value by this key*/
    fun clearPref(prefKey: String) = sharePreferences.edit().remove(prefKey).apply()


}