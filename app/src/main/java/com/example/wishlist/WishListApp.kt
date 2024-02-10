package com.example.wishlist

import android.app.Application

// basically this is the Application class and the entry point of our database.

class WishListApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }

}