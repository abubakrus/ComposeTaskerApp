package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.domain.repositories.TaskCategoryRepository

class AddTaskCategoryUseCase(
    private val repository: TaskCategoryRepository
) {

    operator fun invoke(taskCategory: TaskCategory) {
        repository.addTaskCategory(taskCategory = taskCategory)
    }

}