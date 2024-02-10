package com.example.wishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish_table")
data class Wish(
                @ColumnInfo(name = "wish-title")
                val title: String = "",
                @ColumnInfo(name = "wish-desc")
                val description: String = "",
                @PrimaryKey(autoGenerate = true)
                val id: Long = 0L
                )


object DummyData{

    val wishList = listOf(
        Wish("Google", "join it"),
        Wish("Google", "join it"),
        Wish("Google", "join it"),
        Wish("Google", "join it"),
        Wish("Google", "join it"),
    )

}

