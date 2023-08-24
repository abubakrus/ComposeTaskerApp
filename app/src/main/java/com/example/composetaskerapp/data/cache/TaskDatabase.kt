package com.example.composetaskerapp.data.cache

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composetaskerapp.data.cache.dao.TaskCategoryDao
import com.example.composetaskerapp.data.cache.dao.TaskDao
import com.example.composetaskerapp.data.models.TaskCache
import com.example.composetaskerapp.data.models.TaskCategoryCache

@Database(
    entities = [
        TaskCache::class,
        TaskCategoryCache::class
    ],
    version = 2,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ],
    exportSchema = true
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun fetchTaskDao(): TaskDao

    abstract fun fetchTaskCategoryDao(): TaskCategoryDao

}