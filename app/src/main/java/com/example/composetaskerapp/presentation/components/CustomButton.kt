package com.example.composetaskerapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    onClick: () -> Unit
) {

    Button(
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            ),
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}