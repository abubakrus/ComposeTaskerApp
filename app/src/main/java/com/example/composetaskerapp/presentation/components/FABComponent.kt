package com.example.composetaskerapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetaskerapp.R
import com.example.composetaskerapp.common.extensions.SpacerHeight
import com.example.composetaskerapp.presentation.screens.main_screen.DropDownOptionsItem
import com.example.composetaskerapp.presentation.theme.ComposeTaskerAppTheme
import com.example.composetaskerapp.presentation.theme.LargeSpacing
import com.example.composetaskerapp.presentation.theme.MediumSpacing

@Preview
@Composable
fun FABComponentPreview() {
    ComposeTaskerAppTheme {
        FABComponent(
            onTaskClick = {},
            onTaskCategoryClick = {}
        )
    }
}

@Composable
fun FABComponent(
    modifier: Modifier = Modifier,
    onTaskClick: () -> Unit,
    onTaskCategoryClick: () -> Unit,
) {
    val isClick = remember {
        mutableStateOf(false)
    }
    Column {
        if (isClick.value) FABCategories(
            onTaskClick = onTaskClick,
            onTaskCategoryClick = onTaskCategoryClick
        )
        SpacerHeight(dp = LargeSpacing)
        Card(
            modifier = modifier
                .clip(CircleShape)
                .size(64.dp)
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    isClick.value = !isClick.value
                },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(
                    id = if (isClick.value) R.color.blue
                    else R.color.white
                )
            )
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isClick.value) R.drawable.croos_icon
                        else R.drawable.plus_icon
                    ),
                    contentDescription = null,
                    tint = colorResource(
                        id = if (isClick.value) R.color.white
                        else R.color.blue
                    ),

                    )
            }
        }
    }
}

@Composable
fun FABCategories(
    modifier: Modifier = Modifier,
    onTaskClick: () -> Unit,
    onTaskCategoryClick: () -> Unit,

    ) {
    DropdownMenu(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = MediumSpacing),
        expanded = true,
        onDismissRequest = {},

        ) {
        DropDownOptionsItem(
            textId = R.string.task,
            iconId = R.drawable.task_icon,
            onClick = onTaskClick
        )
        Divider()
        DropDownOptionsItem(
            textId = R.string.category,
            iconId = R.drawable.lists_icon,
            onClick = onTaskCategoryClick
        )
    }
}