package com.noor.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.noor.compose.ui.graphics.DarkColors
import com.noor.compose.ui.graphics.LightColors
import com.noor.compose.ui.graphics.CustomColors


val LocalCustomColors = compositionLocalOf<CustomColors> {
    // Provide a default value in case no value is provided in the CompositionLocalProvider.
    CustomColors.LightCustomColors
}

@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    val extendedColors = if (darkTheme) CustomColors.DarkCustomColors else CustomColors.LightCustomColors

    CompositionLocalProvider(LocalCustomColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}

val MaterialTheme.customTextColors: CustomColors
    @Composable
    get() = LocalCustomColors.current