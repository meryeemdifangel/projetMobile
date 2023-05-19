package com.example.movieexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName="menus")
data class Menu(
    @PrimaryKey
    val menuId:Int,
    val name:String,
    var description:String ,
    val picture : Int,
    val quantity:Int,
    val price :Double,
    val restaurantId:Int,
)
