package com.alikizilcan.stingyapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alikizilcan.stingyapp.data.model.TransactionsEntity
import com.alikizilcan.stingyapp.data.model.UserEntity

@Database(entities = [UserEntity::class, TransactionsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun stingyDao(): StingyDao
}