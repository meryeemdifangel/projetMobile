package com.example.movieexample

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey
    val userId:Int,
    val email:String,
    val password:String,
    val nom:String,
    var prenom:String ,
    val picture : Int
    )