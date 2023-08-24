package com.example.composetaskerapp.data.repository

import com.example.composetaskerapp.common.extensions.toCache
import com.example.composetaskerapp.common.extensions.toCategory
import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.domain.repositories.TaskCategoryRepository
import com.example.composetaskerapp.presentation.TaskApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskCategoryRepositoryImpl : TaskCategoryRepository {

    private val dao = TaskApp.database.fetchTaskCategoryDao()

    override fun addTaskCategory(taskCategory: TaskCategory) {
        dao.addTaskCategory(taskCategory = taskCategory.toCache())
    }

    override fun deleteTaskCategoryById(id: String) {
        dao.deleteTaskCategoryById(id)
    }

    override fun fetchAllTaskCategoriesFlow(): Flow<List<TaskCategory>> {
        return dao.fetchAllTaskCategoriesFlow().map { it.map { it.toCategory() } }
    }

    override fun fetchTaskCategoryById(id: String): TaskCategory {
        return dao.fetchTaskCategoryById(id).toCategory()
    }
}