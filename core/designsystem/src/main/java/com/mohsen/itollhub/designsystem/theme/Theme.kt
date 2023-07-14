package com.mohsen.itollhub.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    primary = RoseBud,
    onPrimary = IndianTan,
    primaryContainer = ReddishBrown,
    onPrimaryContainer = PeachSchnapps,
    secondary = Melrose,
    onSecondary = CatalinaBlue,
    secondaryContainer = Blueberry,
    onSecondaryContainer = HawkesBlue,
    background = Oil,
    onBackground = LavenderPinocchio
)

private val LightColorPalette = lightColorScheme(
    primary = BrownishRed,
    onPrimary = White,
    primaryContainer = PeachSchnapps,
    onPrimaryContainer = Bean,
    secondary = DuskyBlue,
    onSecondary = White,
    secondaryContainer = HawkesBlue,
    onSecondaryContainer = DarkBlue,
    background = MilkWhite,
    onBackground = Gondola

)

@Composable
fun ItollHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        dynamicColor && darkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }

        dynamicColor && !darkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}