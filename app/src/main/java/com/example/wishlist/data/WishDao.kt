package com.example.wishlist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wish: Wish)          // suspend states that function basically modifies database so should be performed in background

    @Query("Select * from `wish_table`")
    abstract fun getAllWishes(): kotlinx.coroutines.flow.Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wish: Wish)

    @Delete
    abstract suspend fun deleteWish(wish: Wish)

    @Query("Select * from `wish_table` where id=:id")
    abstract fun getWishById(id:Long): Flow<Wish>

}