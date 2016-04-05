package com.shpock.kotlin

import android.content.Context
import android.os.Handler
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by Daniel Niedermühlbichler on 04/04/16.
 */
class ItemService(val context: Context, val responseListener: ResponseListener) {

    fun loadItems() {
        Handler().postDelayed(
                { loadFromAssets() },
                2000
        )
    }

    private fun loadFromAssets() {
        val inputStream = context.assets.open("items.json")
        val items = parseJsonFromStream(inputStream)
        responseListener.onResponse(items)
    }

    private fun parseJsonFromStream(inputStream: InputStream?): Array<Item?> {
        return Gson().fromJson<Array<Item?>>(JsonReader(InputStreamReader(inputStream)), Array<Item?>::class.java)
    }

    interface ResponseListener {
        fun onResponse(items: Array<Item?>)
    }

}