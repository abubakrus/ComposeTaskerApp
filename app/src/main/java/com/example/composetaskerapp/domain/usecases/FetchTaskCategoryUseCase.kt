package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.domain.repositories.TaskCategoryRepository

class FetchTaskCategoryUseCase (
    private val repository: TaskCategoryRepository
){
    operator fun invoke(id:String):TaskCategory{
        return repository.fetchTaskCategoryById(id)
    }
}