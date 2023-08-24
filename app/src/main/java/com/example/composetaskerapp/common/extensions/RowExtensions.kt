package com.example.composetaskerapp.common.extensions

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

class RowExtensions {
}

@Composable
fun RowScope.SpacerWidth(dp:Dp) {
    Spacer(modifier = Modifier.width(dp))
}