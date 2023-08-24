package com.example.composetaskerapp.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

fun String.convertToColor():Color{
    return Color(android.graphics.Color.parseColor(this))
}