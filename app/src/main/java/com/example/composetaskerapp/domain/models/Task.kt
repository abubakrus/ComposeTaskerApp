package com.example.composetaskerapp.domain.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import java.util.UUID

data class Task(
    val id: String,
    val title: String,
    val time: String,
    val data: String,
    val categoryId: String,
    val categoryColor: String
) {

}