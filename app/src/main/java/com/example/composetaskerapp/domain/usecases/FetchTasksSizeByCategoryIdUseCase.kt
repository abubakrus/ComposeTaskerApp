package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.data.repository.TaskCategoryRepositoryImpl
import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.domain.repositories.TaskRepository

class FetchTasksSizeByCategoryIdUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(categoryId:String):Int{
        return repository.fetchTasksSizeByCategoryId(categoryId)
    }

}