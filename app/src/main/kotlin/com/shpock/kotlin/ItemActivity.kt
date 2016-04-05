package com.shpock.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_item.*

/**
 * Created by Daniel Niedermühlbichler on 05/04/16.
 */
class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        loadSomeBeautifulImage()
    }

    private fun loadSomeBeautifulImage() {
        val width = (Math.random() * 100 + 500).toInt()
        val height = (Math.random() * 100 + 200).toInt()

        Glide.with(this)
                .load("http://placekitten.com/g/$width/$height")
                .into(imageView)
    }

}