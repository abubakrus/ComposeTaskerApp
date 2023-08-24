package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.domain.repositories.TaskCategoryRepository

class DeleteTaskCategoryUseCase(
    private val repository: TaskCategoryRepository
) {

    operator fun invoke(id:String){
        repository.deleteTaskCategoryById(id)
    }
}