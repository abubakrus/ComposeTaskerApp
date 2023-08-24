package com.example.composetaskerapp.presentation.screens.add_task_category

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@Destination
@Composable
fun AddTaskCategory(
    navigator: DestinationsNavigator
) {
    val viewModel: AddTaskCategoryViewModel = viewModel()

    viewModel.navigateUpFlow.filterNotNull()
        .onEach {
            navigator.navigateUp()
        }.launchIn(rememberCoroutineScope())

    AddTaskCategoryScreen(
        uiState = viewModel.uiState,
        toastFlow = viewModel.toastFlow,
        saveButtonOnClick = viewModel::saveButtonOnClick,
        updateTitle = viewModel::updateTitle,
        updateColorCode = viewModel::updateColorCode,
        onCancelClick = {navigator.navigateUp()}
    )
}