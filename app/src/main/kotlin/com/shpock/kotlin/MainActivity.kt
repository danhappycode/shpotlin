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

        swipeRefreshLayout.setOnRefreshListener { loadItems() }

        loadItems()
    }

    private fun loadItems() {
        showLoadingIndicator()
        ItemService(this, this).loadItems()
    }

    private fun showLoadingIndicator() {
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = true }
    }

    private fun hideLoadingIndicator() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onResponse(items: Array<Item?>) {
        hideLoadingIndicator()
        recyclerView.swapAdapter(ItemAdapter(this, items), true)
    }
}
