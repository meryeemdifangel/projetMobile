package com.example.movieexample

import androidx.room.*

@Dao
interface RestaurantDao {
    @Query("select * from restaurants")
    fun getRestaurants():List<Restaurant>
    @Query("select * from restaurants where restaurantId=(:id_rest)")
    fun getRestaurantById(id_rest: Int):Restaurant
    @Insert
    fun addRestaurant(vararg restaurant: Restaurant)
    @Update
    fun updateRestaurant(restaurant: Restaurant)
    @Delete
    fun deleteRestaurant(restaurant: Restaurant)
}