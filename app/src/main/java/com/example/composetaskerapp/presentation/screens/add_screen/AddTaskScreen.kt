package com.example.composetaskerapp.presentation.screens.add_screen

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetaskerapp.R
import com.example.composetaskerapp.domain.models.TaskCategory
import com.example.composetaskerapp.presentation.components.CircleColorComponents
import com.example.composetaskerapp.presentation.components.DatePickerUI
import com.example.composetaskerapp.presentation.components.TaskCategoryLists
import com.example.composetaskerapp.presentation.components.TimePickerUI
import com.example.composetaskerapp.presentation.components.hours
import com.example.composetaskerapp.common.extensions.SpacerHeight
import com.example.composetaskerapp.common.extensions.SpacerWidth
import com.example.composetaskerapp.common.extensions.convertToColor
import com.example.composetaskerapp.presentation.models.ParametersHeaderType
import com.example.composetaskerapp.presentation.theme.ComposeTaskerAppTheme
import com.example.composetaskerapp.presentation.theme.ExtraLargeSpacing
import com.example.composetaskerapp.presentation.theme.ExtraMediumSpacing
import com.example.composetaskerapp.presentation.theme.LargeSpacing
import com.example.composetaskerapp.presentation.theme.MediumSpacing
import com.example.composetaskerapp.presentation.theme.SmallSpacing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddTaskScreenPreview() {
    ComposeTaskerAppTheme {
        AddTaskScreen(
            uiState = AddTaskScreenUiState(),
            toastFlow = flowOf(),
            onSaveTask = {},
            onCancelClick = {},
            updateSelectedCategory = {},
            updateSelectedDate = {},
            updateSelectedTime = {},
            updateTitle = {}
        )
    }
}

@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    uiState: AddTaskScreenUiState,
    toastFlow: Flow<Int?>,
    onSaveTask: () -> Unit,
    onCancelClick: () -> Unit,
    updateSelectedDate: (String) -> Unit,
    updateSelectedTime: (String) -> Unit,
    updateTitle: (String) -> Unit,
    updateSelectedCategory: (TaskCategory) -> Unit,

    ) {
    var text by remember { mutableStateOf(uiState.title ?: "") }
    var taskParameters by remember { mutableStateOf(ParametersHeaderType.NONE) }
    val context = LocalContext.current

    val toastMessageId = toastFlow.collectAsState(initial = null)

    LaunchedEffect(toastMessageId.value){
        if (toastMessageId.value != null){
            Toast.makeText(
                context,
                toastMessageId.value!!,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        AddTaskToolbar(
            onSaveTask = onSaveTask,
            onCancelClick = onCancelClick,
        )

        UserInputBlock(
            text = text,
            selectedTime = uiState.selectedTime,
            selectedDate = uiState.selectedDate,
            onValueChange = { value ->
                text = value
                updateTitle(value)
            },
        )
        Spacer(modifier = modifier.weight(1f))
        ParametersHeader(
            onClick = { parameter ->
                taskParameters = parameter
            },
            taskCategory = TaskCategory.preview
        )
        AnimatedVisibility(visible = taskParameters == ParametersHeaderType.CATEGORY) {
            TaskCategoryLists(
                modifier = modifier.height(400.dp),
                categories = uiState.tasksCategories,
                onClick = {
                    updateSelectedCategory(it)
                }
            )
        }
        AnimatedVisibility(visible = taskParameters == ParametersHeaderType.DATE) {
            DatePickerUI(
                onDateSelectedListener = {
                    updateSelectedDate("${it.dayOfMonth}.${it.monthValue}.${it.year}")
                },
                height = 220.dp,
                textStyle = MaterialTheme.typography.titleLarge
            )
        }
        AnimatedVisibility(visible = taskParameters == ParametersHeaderType.TIME) {
            TimePickerUI(
                onTimeSelectedListener = { hours, minute ->
                    updateSelectedTime("$hours:$minute")
                },
                height = 220.dp,
                textStyle = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun AddTaskToolbar(
    modifier: Modifier = Modifier,
    onSaveTask: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = LargeSpacing,
                vertical = ExtraMediumSpacing
            )
            .fillMaxWidth()
    ) {
        Text(
            modifier = modifier
                .clickable { onCancelClick() },
            text = stringResource(id = R.string.cancel),
            style = MaterialTheme.typography.titleSmall.copy(
                color = colorResource(id = R.color.blue),
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            modifier = modifier
                .clickable { onSaveTask() },
            text = stringResource(id = R.string.done),
            style = MaterialTheme.typography.titleSmall.copy(
                color = colorResource(id = R.color.blue),
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputBlock(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    selectedDate: String?,
    selectedTime: String?,

    ) {
    Row(
        modifier = modifier
            .padding(
                vertical = LargeSpacing + SmallSpacing,
                horizontal = LargeSpacing
            )
            .fillMaxWidth(),
    ) {
        Icon(
            modifier = modifier.padding(top = LargeSpacing),
            painter = painterResource(id = R.drawable.unmarked_icon),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.White
            else colorResource(id = R.color.large_gray)
        )
        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            TextField(
                value = text, onValueChange = onValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.titleSmall,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.what),
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = if (isSystemInDarkTheme()) Color.White
                            else colorResource(id = R.color.large_gray)
                        )
                    )

                }
            )
            Row(
                modifier = modifier
            ) {
                if (selectedTime != null) {
                    SpacerWidth(dp = ExtraMediumSpacing)
                    Icon(
                        modifier = modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.alarm_icon),
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Color.White
                        else colorResource(id = R.color.large_gray)
                    )
                    SpacerWidth(dp = MediumSpacing)
                    Text(
                        text = selectedTime,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (isSystemInDarkTheme()) Color.White
                            else colorResource(id = R.color.large_gray)
                        )
                    )
                }
                if (selectedDate != null) {
                    SpacerWidth(dp = ExtraMediumSpacing)
                    Icon(
                        modifier = modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.calendar_icon),
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Color.White
                        else colorResource(id = R.color.large_gray)
                    )
                    SpacerWidth(dp = MediumSpacing)
                    Text(
                        text = selectedDate,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (isSystemInDarkTheme()) Color.White
                            else colorResource(id = R.color.large_gray)
                        )
                    )
                }


            }
        }

        CircleColorComponents(
            modifier = modifier
                .padding(top = ExtraLargeSpacing)
                .size(12.dp),
            color = Color.Blue
        )
    }
}

@Composable
fun ParametersHeader(
    modifier: Modifier = Modifier,
    onClick: (ParametersHeaderType) -> Unit,
    taskCategory: TaskCategory
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing)
    ) {
        Divider(
            color = if (isSystemInDarkTheme()) Color.White
            else colorResource(id = R.color.large_gray)
        )
        SpacerHeight(dp = LargeSpacing + SmallSpacing)
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = modifier.size(24.dp),
                onClick = {
                    onClick(ParametersHeaderType.DATE)
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else colorResource(id = R.color.large_gray)
                )
            }
            SpacerWidth(dp = 26.dp)
            IconButton(
                modifier = modifier.size(24.dp),
                onClick = {
                    onClick(ParametersHeaderType.TIME)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alarm_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.large_gray)
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(
                modifier = modifier
                    .clickable {
                        onClick(ParametersHeaderType.CATEGORY)
                    },
                text = stringResource(id = R.string.inbox),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = colorResource(id = R.color.large_gray),
                    fontWeight = FontWeight.Medium
                )
            )
            SpacerWidth(dp = MediumSpacing)
            CircleColorComponents(
                modifier = modifier
                    .clickable {
                        onClick(ParametersHeaderType.CATEGORY)
                    }
                    .size(12.dp),
                color = taskCategory.colorCode.convertToColor()
            )
            SpacerWidth(dp = SmallSpacing)
        }
        SpacerHeight(dp = SmallSpacing)
    }

}