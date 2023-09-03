package com.example.composetaskerapp.presentation.screens.main_screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetaskerapp.presentation.models.TaskUi
import com.example.composetaskerapp.presentation.screens.destinations.AddTaskCategoryDestination
import com.example.composetaskerapp.presentation.screens.destinations.AddTaskDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun Main(
    navigator: DestinationsNavigator
) {
    val viewModel: MainViewModel = viewModel()
    MainScreen(
        onTaskPopupClick = {
            navigator.navigate(AddTaskDestination(null))
        },
        onTaskCategoryClick = {
            navigator.navigate(AddTaskCategoryDestination)
        },
        uiState = viewModel.uiState,
        onSelectItem = viewModel::onSelectItem,
        onRemoveSelectedItems = viewModel::onRemoveSelectedItems,
        onSelectedAllItems = viewModel::onSelectedAllItems,
        onUnSelectedAllItems = viewModel::onUnSelectedAllItems,
        onTaskClick = { task -> navigator.navigate(AddTaskDestination(task)) }
    )
}