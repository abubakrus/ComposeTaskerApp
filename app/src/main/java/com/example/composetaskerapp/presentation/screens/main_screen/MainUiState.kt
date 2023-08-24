package com.example.composetaskerapp.presentation.screens.main_screen

import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.presentation.models.TaskUi

data class MainUiState(
    val tasks: List<TaskUi> = emptyList(),
    val taskCategory: List<Pair<TaskCategory, Int>> = emptyList(),
    val selectedTasks: Set<TaskUi> = emptySet(),
)