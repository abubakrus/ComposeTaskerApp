package com.example.composetaskerapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composetaskerapp.R
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerUI(
    modifier: Modifier = Modifier,
    onDateSelectedListener: (LocalDate) -> Unit,
    height: Dp,
    textStyle: TextStyle,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        Spacer(modifier = modifier.height(30.dp))

        val chosenYear = remember { mutableStateOf(currentYear) }
        val chosenMonth = remember { mutableStateOf(currentMonth) }
        val chosenDay = remember { mutableStateOf(currentDay) }

        DateSelectionSection(
            onYearChosen = { chosenYear.value = it.toInt() },
            onMonthChosen = { chosenMonth.value = (monthsNames.indexOf(it) + 1) },
            onDayChosen = { chosenDay.value = it.toInt() },
            height = height,
            textStyle = textStyle
        )

        Spacer(modifier = modifier.height(30.dp))

        CustomButton(
            titleId = R.string.select
        ) {
            onDateSelectedListener(
                LocalDate.of(
                    chosenYear.value,
                    chosenMonth.value,
                    chosenDay.value
                )
            )
        }
    }
}

@Composable
fun DateSelectionSection(
    modifier: Modifier = Modifier,
    onYearChosen: (String) -> Unit,
    onMonthChosen: (String) -> Unit,
    onDayChosen: (String) -> Unit,
    height: Dp,
    textStyle: TextStyle,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        InfiniteItemsPicker(
            items = days,
            firstIndex = Int.MAX_VALUE / 2 + (currentDay - 2),
            onItemSelected = onDayChosen,
            textStyle = textStyle
        )

        InfiniteItemsPicker(
            items = monthsNames,
            firstIndex = Int.MAX_VALUE / 2 - 4 + currentMonth,
            onItemSelected = onMonthChosen,
            textStyle = textStyle
        )

        InfiniteItemsPicker(
            items = years,
            firstIndex = Int.MAX_VALUE / 2 + (currentYear - 1967),
            onItemSelected = onYearChosen,
            textStyle = textStyle
        )
    }
}


fun Modifier.fadingEdge(brush: Brush) = this
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }