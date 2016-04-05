package com.shpock.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemService.ResponseListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter(this)

        loadItems()
    }

    private fun loadItems() {
        ItemService(this, this).loadItems()
    }

    override fun onResponse(items: Array<Item?>) {
        recyclerView.swapAdapter(ItemAdapter(this, items), true)
    }
}
