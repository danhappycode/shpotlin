package com.shpock.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemService.ResponseListener {

    private lateinit var tracking: Tracking

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tracking = Tracking(getPreferences(MODE_PRIVATE))

        setUpRecyclerView()
        setUpSwipeRefresh()

        loadItems()
    }

    private fun setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener { loadItems() }
    }

    private fun setUpRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter(this)
    }

    private fun loadItems() {
        showLoadingIndicator()
        ItemService(this, this).loadItems()
        increaseReloadCount()
    }

    private fun increaseReloadCount() {
        tracking.reloadCount++
        Toast.makeText(this, "reload count: ${tracking.reloadCount}", Toast.LENGTH_SHORT).show()
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
