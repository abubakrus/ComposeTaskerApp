package com.example.composetaskerapp.domain.usecases

import com.example.composetaskerapp.domain.models.Task
import com.example.composetaskerapp.domain.repositories.TaskRepository

class UpdateTaskUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(task: Task) {
        repository.updateTask(task = task)
    }
}