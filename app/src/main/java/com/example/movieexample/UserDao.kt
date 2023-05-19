package com.example.movieexample

import androidx.room.*
@Dao
interface UserDao {
    @Insert
    fun insert(vararg user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users WHERE userId = (:userId)")
    fun getUser(userId: Int): User?



    @Query("select * from users")
    fun getAllUsers(): List<User>
}