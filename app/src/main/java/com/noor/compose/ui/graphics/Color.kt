package com.noor.compose.ui.graphics

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Define individual colors
val PrimaryColor = Color(0xFF0182AA)
val PrimaryVariant = Color(0xFF00539F)
val SecondaryColor = Color(0xFFFBD012)
val BackgroundColor = Color(0xFFFFFFFF)
val SurfaceColor = Color(0xFFEEEEEE)
val ErrorColor = Color(0xFFB00020)

//Charcoals
val Charcoal100 = Color(0xFF4A4A4A)
val Charcoal80 = Color(0xFF6E6E6E)
val Charcoal60 = Color(0xFF929292)
val Charcoal20 = Color(0xFFDBDBDB)
val Charcoal10 = Color(0xFFECECEC)
val Charcoal05 = Color(0xFFF6F6F6)

//Color Control
val Disabled = Color(0xFFCCCCCC)
val DisabledPrimaryButton = Disabled


// Light Theme Palette
val LightColors = lightColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariant,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    error = ErrorColor,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

// Dark Theme Palette
val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariant,
    secondary = SecondaryColor,
    background = Color.Black,
    surface = Color.DarkGray,
    error = ErrorColor,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

abstract class CustomColors(
    val primary: Color,
    val secondary: Color,
    val light: Color,
    val white: Color,
    val hint: Color,
    val textFieldBackgroundColor: Color,
    ) {
    object LightCustomColors : CustomColors(
        primary = Charcoal100,
        secondary = Color.White,
        light = Color(0xFF777777),
        white = Color.White,
        hint = Charcoal60,
        textFieldBackgroundColor = Charcoal05
    )

    //For now i am just going to use Light text colors
    object DarkCustomColors : CustomColors(
        primary = Color.White,
        secondary = Color.White,
        light = Color(0xFF777777),
        white = Color.White,
        hint = Charcoal60,
        textFieldBackgroundColor = Charcoal05
    )
}