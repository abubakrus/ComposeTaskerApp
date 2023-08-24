package com.example.composetaskerapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.common.extensions.SpacerHeight
import com.example.composetaskerapp.common.extensions.convertToColor
import com.example.composetaskerapp.presentation.theme.ExtraMediumSpacing
import com.example.composetaskerapp.presentation.theme.LargeSpacing
import com.example.composetaskerapp.presentation.theme.MediumSpacing
import com.example.composetaskerapp.presentation.theme.SmallSpacing
import kotlin.jvm.internal.FunInterfaceConstructorReference

@Preview
@Composable
fun TaskCategoryPreview() {
    TaskCategoryItem(category = TaskCategory.preview)
}

@Composable
fun TaskCategoryLists(
    modifier: Modifier = Modifier,
    categories: List<TaskCategory>,
    onClick: (TaskCategory) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = categories,
            key = { item -> item.id }
        ) { category ->
            Column(
                modifier = Modifier
                    .clickable { onClick(category) }
                    .padding(
                        horizontal = MediumSpacing
                    )
            ) {
                SpacerHeight(dp = SmallSpacing)
                TaskCategoryItem(
                    category = category
                )
                SpacerHeight(dp = SmallSpacing)
            }

        }
    }
}

@Composable
fun TaskCategoryItem(
    modifier: Modifier = Modifier,
    category: TaskCategory,
    count: Int? = null
) {
    Box(
        modifier = modifier
            .height(70.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(category.colorCode.convertToColor())
    ) {
        Column(
            modifier = modifier
                .padding(start = LargeSpacing)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            SpacerHeight(dp = ExtraMediumSpacing)
            Text(
                text = category.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
            if (count != null) {
                SpacerHeight(dp = SmallSpacing)
                Text(
                    text = "$count tasks",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black
                    )
                )
            }
        }
    }
}