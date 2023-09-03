package com.example.composetaskerapp.presentation.screens.add_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetaskerapp.R
import com.example.composetaskerapp.common.extensions.mapToTask
import com.example.composetaskerapp.data.repository.TaskCategoryRepositoryImpl
import com.example.composetaskerapp.data.repository.TaskRepositoryImpl
import com.example.composetaskerapp.domain.models.Task
import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.domain.repositories.TaskRepository
import com.example.composetaskerapp.domain.usecases.AddNewTasksUseCase
import com.example.composetaskerapp.domain.usecases.FetchAllTaskCategoryUseCase
import com.example.composetaskerapp.domain.usecases.UpdateTaskUseCase
import com.example.composetaskerapp.presentation.models.TaskUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import java.util.concurrent.atomic.AtomicBoolean

class AddTaskViewModel : ViewModel() {

    private val repository: TaskRepository = TaskRepositoryImpl()
    private val taskCategoryRepository = TaskCategoryRepositoryImpl()


    private val addNewTasksUseCase = AddNewTasksUseCase(repository)
    private val fetchAllTaskUseCase = FetchAllTaskCategoryUseCase(taskCategoryRepository)
    private val updateTaskUseCase = UpdateTaskUseCase(repository)

    var uiState by mutableStateOf(AddTaskScreenUiState())

    private val _toastFlow = MutableStateFlow<Int?>(null)
    val toastFlow = _toastFlow.asStateFlow()

    private val _navigateUpFlow = MutableStateFlow<Unit?>(null)
    val navigateUpFlow = _navigateUpFlow.asStateFlow()

    private var taskId: String? = null

    init {
        fetchAllTaskUseCase().onEach { taskCategories ->
            uiState = uiState.copy(tasksCategories = taskCategories)
        }.launchIn(viewModelScope)
    }

    private val isFirstOpen = AtomicBoolean(false)
    fun updateUiTask(taskUi: TaskUi?) {
        if (taskUi == null) return
        if (isFirstOpen.compareAndSet(false, true)){
            taskId = taskUi.id
            uiState = uiState.copy(
                selectedDate = taskUi.data,
                selectedTime = taskUi.time,
                selectedCategory = TaskCategory(
                    id = taskUi.id,
                    colorCode = taskUi.categoryColor,
                    title = ""
                ),
                title = taskUi.title
            )
        }

    }

    fun updateSelectedDate(date: String) {
        uiState = uiState.copy(selectedDate = date)
    }

    fun updateSelectedTime(time: String) {
        uiState = uiState.copy(selectedTime = time)
    }

    fun updateSelectedCategory(category: TaskCategory) {
        uiState = uiState.copy(selectedCategory = category)
    }

    fun updateTitle(title: String) {
        uiState = uiState.copy(title = title)
    }

    fun updateTask() {
        val task = fetchTaskByUiState()
        updateTaskUseCase(task)
        _navigateUpFlow.tryEmit(Unit)
    }

    fun addNewTask() {
        if (uiState.title.isNullOrBlank()) {
            _toastFlow.tryEmit(R.string.error_empty_title)
            return
        }
        if (uiState.selectedCategory == null) {
            _toastFlow.tryEmit(R.string.error_empty_selected_categories)
            return
        }
        if (uiState.selectedDate.isNullOrBlank()) {
            _toastFlow.tryEmit(R.string.error_empty_selected_date)
            return
        }
        if (uiState.selectedTime.isNullOrBlank()) {
            _toastFlow.tryEmit(R.string.error_empty_selected_time)
            return
        }
        val task = fetchTaskByUiState()
        addNewTasksUseCase(task)
        uiState = AddTaskScreenUiState()
        _navigateUpFlow.tryEmit(Unit)
    }

    private fun fetchTaskByUiState(): Task = Task(
        id = taskId ?: UUID.randomUUID().toString(),
        time = uiState.selectedTime!!,
        data = uiState.selectedDate!!,
        categoryId = uiState.selectedCategory!!.id,
        title = uiState.title!!,
        categoryColor = uiState.selectedCategory!!.colorCode
    )
}