package com.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var adapter:
    lateinit var userVerticalAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDataInRecyclerView()
    }

    private fun setupDataInRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        userVerticalAdapter = UsersAdapter(DataSource.getUser())
        val listOfAdapters = listOf(userVerticalAdapter)
        adapter = ConcatAdapter(listOfAdapters)
        recyclerView.adapter = adapter
    }
}