package com.example.composetaskerapp.presentation.screens.add_screen

import android.icu.text.CaseMap.Title
import com.example.composetaskerapp.domain.models.TaskCategory
import java.time.LocalDate

data class AddTaskScreenUiState(
    val selectedCategory: TaskCategory? = null,
    val selectedDate: String? = null,
    val selectedTime: String? = null,
    val title: String? = null,
    val tasksCategories:List<TaskCategory> = emptyList()
)