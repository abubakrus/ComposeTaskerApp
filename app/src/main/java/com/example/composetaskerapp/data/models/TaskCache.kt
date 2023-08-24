package com.example.composetaskerapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskCache(
    @PrimaryKey val id: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("time") val time: String,
    @ColumnInfo("data") val data: String,
    @ColumnInfo("category_id") val categoryId: String,
    @ColumnInfo("category_color") val categoryColor: String
)