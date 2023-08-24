package com.example.composetaskerapp.presentation

import android.app.Application
import androidx.room.Room
import com.example.composetaskerapp.data.cache.TaskDatabase

class TaskApp : Application() {

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            TaskDatabase::class.java,
            "task_app_database"
        ).allowMainThreadQueries().build()
    }


    companion object {
        lateinit var database: TaskDatabase
    }
}