package com.example.composetaskerapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetaskerapp.R
import com.example.composetaskerapp.domain.models.Task
import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.common.extensions.convertToColor
import com.example.composetaskerapp.presentation.models.TaskUi
import com.example.composetaskerapp.presentation.theme.ComposeTaskerAppTheme
import com.example.composetaskerapp.presentation.theme.ExtraLargeSpacing
import com.example.composetaskerapp.presentation.theme.LargeSpacing
import com.example.composetaskerapp.presentation.theme.MediumSpacing
import com.example.composetaskerapp.presentation.theme.SmallSpacing

@Preview
@Composable
fun TaskItemPreview() {
    ComposeTaskerAppTheme {
        TaskItem(
            task = TaskUi.preview,
            onClick = {},
            onSelected = { _, _ -> },
            isSelected = true
        )
    }
}

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: TaskUi,
    onClick: (TaskUi) -> Unit,
    onSelected: (TaskUi, Boolean) -> Unit,
    isSelected: Boolean
) {

    val greyColor = colorResource(id = R.color.grey)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = LargeSpacing)
            .clickable { onClick(task) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onSelected(task, !isSelected)
        }) {
            val unCheckerColor = if (isSystemInDarkTheme()) Color.White
            else colorResource(id = R.color.large_gray)
            Icon(
                painter = painterResource(
                    id = if (isSelected) R.drawable.marked_icon
                    else R.drawable.unmarked_icon
                ),
                contentDescription = null,
                tint = if (isSelected) colorResource(id = R.color.blue)
                else unCheckerColor
            )
        }
        Column(
            modifier = modifier
                .padding(horizontal = LargeSpacing)
                .weight(1f),
        ) {
            Text(
                text = task.title, style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Row(
                modifier = modifier.padding(top = SmallSpacing),
            ) {
                Icon(
                    modifier = modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.alarm_icon),
                    contentDescription = null,
                    tint = greyColor
                )
                Spacer(modifier = modifier.width(SmallSpacing))
                Text(
                    text = task.time, style = MaterialTheme.typography.bodyMedium.copy(
                        color = greyColor, fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = modifier.width(MediumSpacing))
                Icon(
                    modifier = modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = null,
                    tint = greyColor
                )
                Spacer(modifier = modifier.width(SmallSpacing))
                Text(
                    text = task.data, style = MaterialTheme.typography.bodyMedium.copy(
                        color = greyColor, fontWeight = FontWeight.Medium
                    )
                )
            }
        }
        CircleColorComponents(
            modifier = modifier.size(12.dp), color = task.categoryColor.convertToColor()
        )
    }
}
