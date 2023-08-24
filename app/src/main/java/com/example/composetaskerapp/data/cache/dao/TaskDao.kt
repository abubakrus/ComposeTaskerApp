package com.example.composetaskerapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composetaskerapp.data.models.TaskCache
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewTask(task: TaskCache)

    @Query("SELECT * FROM task_table")
    fun fetchAllTasks(): List<TaskCache>

    @Query("SELECT * FROM task_table")
    fun fetchAllTasksFlow(): Flow<List<TaskCache>>

    @Query("SELECT * FROM task_table WHERE category_id = :categoryId")
    fun fetchTasksSizeByCategoryId(categoryId:String):List<TaskCache>

    @Query("DELETE FROM task_table WHERE id IN (:idList)")
    fun removeTasksByIds(idList: List<String>)
}