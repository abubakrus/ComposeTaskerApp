package com.example.composetaskerapp.presentation.models

import java.util.UUID

data class TaskUi(
    val id: String,
    val title: String,
    val time: String,
    val data: String,
    val categoryId: String,
    val categoryColor: String,
    var isSelected: Boolean = false,

) {
    companion object {
        val preview = TaskUi(
            id = UUID.randomUUID().toString(),
            time = "22.22",
            data = "02.02.02",
            title = "Go To School",
            categoryId = "",
            categoryColor = ""
        )
        val previews = listOf(
            preview.copy(
                id = UUID.randomUUID().toString(),
            ),
            preview.copy(
                id = UUID.randomUUID().toString(),
            ),
            preview.copy(
                id = UUID.randomUUID().toString(),
            ),
            preview.copy(
                id = UUID.randomUUID().toString(),
            ),
        )
    }
}
