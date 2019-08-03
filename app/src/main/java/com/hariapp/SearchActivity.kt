package com.hariapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_search)
    }
}
