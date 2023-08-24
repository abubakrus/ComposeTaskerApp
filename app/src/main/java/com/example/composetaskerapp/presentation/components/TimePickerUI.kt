package com.example.composetaskerapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composetaskerapp.R
import java.lang.Exception

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePickerUI(
    modifier: Modifier = Modifier,
    onTimeSelectedListener: (Int, Int) -> Unit,
    height: Dp,
    textStyle: TextStyle,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {

        Spacer(modifier = modifier.height(30.dp))

        val chosenHour = remember { mutableStateOf(currentHour) }
        val chosenMinute = remember { mutableStateOf(currentMinute) }

        TimeSelectionSection(
            onHourChosen = {
                try {
                    chosenHour.value = it.toInt()
                } catch (e: Exception) {

                }
            },
            onMinuteChosen = {
                try {
                    chosenMinute.value = it.toInt()
                } catch (e: Exception) {

                }
            },
            height = height,
            textStyle = textStyle
        )

        Spacer(modifier = modifier.height(15.dp))

        CustomButton(
            titleId = R.string.select
        ) {
            onTimeSelectedListener(chosenHour.value, chosenMinute.value)
        }
    }
}

@Composable
fun TimeSelectionSection(
    modifier: Modifier = Modifier,
    onHourChosen: (String) -> Unit,
    onMinuteChosen: (String) -> Unit,
    height: Dp,
    textStyle: TextStyle,
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        InfiniteItemsPicker(
            items = hours,
            firstIndex = Int.MAX_VALUE / 2 + currentHour,
            onItemSelected = onHourChosen,
            textStyle = textStyle
        )
        Spacer(modifier = modifier.width(30.dp))

        InfiniteItemsPicker(
            items = minutes,
            firstIndex = Int.MAX_VALUE / 2 + currentMinute,
            onItemSelected = onMinuteChosen,
            textStyle = textStyle
        )
    }
}