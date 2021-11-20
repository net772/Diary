package com.example.a2alertdialog

import android.content.Context

object PreferenceHelper {
    /** SharedPreferences 객체 반환 */
    private fun getPreference(context: Context) =
        context.getSharedPreferences("password", Context.MODE_PRIVATE)

    fun setPreference(context: Context, key: String, value: String) {
        val prefs = getPreference(context)
        prefs.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun getString(context: Context, key: String, default: String = "000"): String =
        getPreference(context).getString(key, default) ?: default


}