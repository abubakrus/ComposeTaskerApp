package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.domain.models.Task
import com.example.composetaskerapp.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow

class FetchAllTasksUseCase(
    private val repository: TaskRepository
) {

    operator fun invoke():Flow<List<Task>>{
        return repository.fetchAllTasksFlow()
    }
}