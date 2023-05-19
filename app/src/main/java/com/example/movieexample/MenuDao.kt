package com.example.movieexample

import androidx.room.*

@Dao
interface MenuDao {
    @Query("select * from menus")
    fun getMenus():MutableList<Menu>

    @Query("select * from menus where menuId=:id_menu")
    fun getMenuById(id_menu: Int):Menu

    @Query("select * from menus where restaurantId=:id_rest")
    fun getRestaurantMenus(id_rest:Int):List<Menu>

    @Insert
    fun addMenu(vararg menu:Menu)

    @Update
    fun updateMenu(menu:Menu)

    @Delete
    fun deleteMenu(menu:Menu)

    @Query("delete from menus")
    fun deleteAllMenus()

    // Managing the Cart
    // We will modify the quantity attribute only
    // if quantity > 0, it will be on the cart
    // if quantity = 0, it will not be on the cart
    @Query("select * from menus where quantity > 0")
    fun getCart():MutableList<Menu>

    @Query("update menus set quantity = :quantity where menuId= :menuId")
    fun addToCart(menuId:Int, quantity:Int)

    @Query("update menus set quantity = 0")
    fun deleteCart()

    @Query("update menus set quantity = 0 where menuId = :menuId")
    fun deleteMenuFromCart(menuId:Int)

    @Query("select restaurantId from menus where quantity > 0")
    fun getAllRestaurants():List<Int>
}