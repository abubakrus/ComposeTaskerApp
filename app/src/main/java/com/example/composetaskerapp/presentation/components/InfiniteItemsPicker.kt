package com.example.composetaskerapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun InfiniteItemsPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
    textStyle: TextStyle,
) {

    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    val topBottomFade = Brush.verticalGradient(
        0f to Color.Transparent,
        0.3f to Color.Red,
        0.7f to Color.Red,
        1f to Color.Transparent
    )

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE) {
                    val index = it % items.size
                    if (it == remember { derivedStateOf { listState.firstVisibleItemIndex } }.value + 2) {
                        currentValue.value = items[index]
                    }
                    Spacer(modifier = modifier.height(6.dp))

                    Text(
                        text = items[index],
                        modifier = modifier.alpha(if (it == remember { derivedStateOf { listState.firstVisibleItemIndex } }.value + 2) 1f else 0.5f),
                        style = textStyle,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        )

    }
}


val currentYear = Calendar.getInstance().get(Calendar.YEAR)
val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
val currentHour = Calendar.getInstance().get(Calendar.HOUR)
val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)

val years = (1950..2050).map { it.toString() }
val monthsNumber = (1..12).map { it.toString() }
val days = (1..31).map { it.toString() }
val hours = (0..9).map { "0$it" } + (10..24).map { it.toString() }
val minutes = (0..9).map { "0$it" } + (10..59).map { it.toString() }

val monthsNames = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
)