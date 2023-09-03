package com.example.composetaskerapp.presentation.screens.add_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetaskerapp.presentation.models.TaskUi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Destination(start = false)
@Composable
fun AddTask(
    navigator: DestinationsNavigator,
    taskUi: TaskUi? = null
) {
    val viewModel: AddTaskViewModel = viewModel()

    viewModel.updateUiTask(taskUi)

    viewModel.navigateUpFlow.filterNotNull()
        .onEach {
            navigator.navigateUp()
        }.launchIn(rememberCoroutineScope())

    AddTaskScreen(
        uiState = viewModel.uiState,
        onSaveTask = {
            if (taskUi == null) {
                viewModel.addNewTask()
            } else {
                viewModel.updateTask()
            }
        },
        toastFlow = viewModel.toastFlow,
        onCancelClick = navigator::navigateUp,
        updateSelectedCategory = viewModel::updateSelectedCategory,
        updateSelectedDate = viewModel::updateSelectedDate,
        updateSelectedTime = viewModel::updateSelectedTime,
        updateTitle = viewModel::updateTitle,
        task = taskUi,

        )
}