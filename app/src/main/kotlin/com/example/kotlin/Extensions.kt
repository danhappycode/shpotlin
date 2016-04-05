package com.example.kotlin

import android.content.Intent
import com.google.gson.Gson


/**
 * Created by Daniel Niedermühlbichler on 05/04/16.
 */

fun Intent.putJsonExtra(key: String, extra: Any?) {
    this.putExtra(key, Gson().toJson(extra))
}

fun <T> Intent.getJsonExtra(key: String, type: Class<T>): T {
    val jsonString = this.getStringExtra(key)
    return Gson().fromJson(jsonString, type)
}