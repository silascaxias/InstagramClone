package com.example.instagramclone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.instagramclone.R

val RobotoFontFamily = FontFamily(
	Font(R.font.roboto_regular, FontWeight.Normal),
	Font(R.font.roboto_medium, FontWeight.Medium),
	Font(R.font.roboto_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
	bodyLarge = TextStyle(
		fontFamily = RobotoFontFamily,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	),
	titleLarge = TextStyle(
		fontFamily = RobotoFontFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 22.sp
	)
)