package com.example.movieexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Menu::class],version = 5)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getMenuDao(): MenuDao
   // abstract fun getUserDao(): UserDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, AppDatabase::class.java,
                        "app_db").fallbackToDestructiveMigration().allowMainThreadQueries().build() }
            return INSTANCE
        }
    }
}