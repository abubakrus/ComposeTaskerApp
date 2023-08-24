package com.example.composetaskerapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composetaskerapp.R

val SF_PRO_TEXT = FontFamily(
    Font(R.font.sf_pro_text_regular, FontWeight.Normal),
    Font(R.font.sf_pro_text_bold, FontWeight.Bold),
    Font(R.font.sf_pro_text_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_text_light, FontWeight.Light),
    Font(R.font.sf_pro_text_medium, FontWeight.Medium),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineLarge = TextStyle(
        fontFamily = SF_PRO_TEXT,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    ),
    )