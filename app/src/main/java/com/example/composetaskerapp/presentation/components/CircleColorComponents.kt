package com.example.composetaskerapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color


@Composable
fun CircleColorComponents(
    modifier: Modifier = Modifier,
    color: Color
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color)
    )
}