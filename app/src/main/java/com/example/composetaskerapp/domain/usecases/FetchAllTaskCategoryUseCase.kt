package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.domain.repositories.TaskCategoryRepository
import kotlinx.coroutines.flow.Flow

class FetchAllTaskCategoryUseCase(
    private val repository: TaskCategoryRepository
) {
    operator fun invoke():Flow<List<TaskCategory>>{
        return repository.fetchAllTaskCategoriesFlow()
    }
}