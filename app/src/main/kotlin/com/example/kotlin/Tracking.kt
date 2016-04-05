package com.example.kotlin

import android.content.SharedPreferences


/**
 * Created by Daniel Niedermühlbichler on 05/04/16.
 */
class Tracking(private val preferences: SharedPreferences) {

    var reloadCount: Int = -1
        get() {
            if (field == -1) {
                field = preferences.getInt(PREF_RELOAD_COUNT, 0)
            }
            return field
        }
        set(value) {
            field = value
            preferences.edit().putInt(PREF_RELOAD_COUNT, value).apply()
        }


    private val PREF_RELOAD_COUNT = "pref_reload_count"
}