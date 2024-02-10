package com.example.wishlist

import android.content.Context
import androidx.room.Room
import com.example.wishlist.data.WishDatabase
import com.example.wishlist.data.WishRepository

/* basically here object graph is an object of a singleton class which means that a class with only one instance, like there can't be another Graph object and
basically it automates during app initialization*/

object Graph {

    // getting the database
    lateinit var database: WishDatabase

    // setting up the repository (lazy states that dont just load everything at the start, load it as per the requirement)
    val wishRepository by lazy {
        WishRepository(wishDao = database.WishDao())
    }

    // initialize the database
    fun provide(context: Context) {
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishList.db").build()
    }
}