package com.example.composetaskerapp.data.repository

import com.example.composetaskerapp.common.extensions.mapToCache
import com.example.composetaskerapp.common.extensions.mapToTask
import com.example.composetaskerapp.domain.models.Task
import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.domain.repositories.TaskRepository
import com.example.composetaskerapp.presentation.TaskApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl() : TaskRepository {

    private val dao = TaskApp.database.fetchTaskDao()


    override fun fetchAllTasksFlow(): Flow<List<Task>> {
        return dao.fetchAllTasksFlow()
            .map { it.map { it.mapToTask() } }
    }

    override fun fetchAllTaskCategory(): List<TaskCategory> {
        TODO("Not yet implemented")
    }

    override fun fetchTasksByCategoryId(categoryId: String): List<Task> {
        TODO("Not yet implemented")
    }

    override fun fetchTasksSizeByCategoryId(categoryId: String): Int {
        return dao.fetchTasksSizeByCategoryId(categoryId).size
    }

    override fun fetchTaskById(taskId: String): Task {
        TODO("Not yet implemented")
    }

    override fun fetchTaskCategoryById(categoryId: String): TaskCategory {
        TODO("Not yet implemented")
    }

    override fun addNewTask(task: Task): Boolean {
        dao.addNewTask(task.mapToCache())
        return true
    }

    override fun updateTask(task: Task) {
        dao.updateTask(task.mapToCache())
    }

    override fun removeTaskById(id: String) {
        TODO("Not yet implemented")
    }

    override fun removeTasksByIds(ids: List<String>) {
        dao.removeTasksByIds(ids)
    }
}