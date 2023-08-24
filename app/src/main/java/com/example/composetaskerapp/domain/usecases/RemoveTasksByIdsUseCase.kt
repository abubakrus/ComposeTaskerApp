package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.domain.repositories.TaskRepository

class RemoveTasksByIdsUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(ids: List<String>){
        repository.removeTasksByIds(ids)
    }
}