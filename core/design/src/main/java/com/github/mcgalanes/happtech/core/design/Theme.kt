package com.github.mcgalanes.happtech.core.design

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

private val DarkColorScheme =
    darkColorScheme(
        primary = Green80,
        onPrimary = Color.White,
        primaryContainer = Green10,
        onPrimaryContainer = Color.White,
        inversePrimary = Green40,
        secondary = Blue80,
        onSecondary = Blue20,
        secondaryContainer = Blue10,
        onSecondaryContainer = Color.White,
        tertiary = Blue80,
        onTertiary = Blue20,
        tertiaryContainer = Blue30,
        onTertiaryContainer = Blue90,
        error = Red80,
        onError = Red20,
        errorContainer = Red30,
        onErrorContainer = Red90,
        background = Grey10,
        onBackground = Grey90,
        surface = Grey10,
        onSurface = Grey90,
        inverseSurface = Grey90,
        inverseOnSurface = Grey10,
        surfaceVariant = GreenGrey30,
        onSurfaceVariant = GreenGrey80,
        outline = GreenGrey80,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = Green20,
        onPrimary = Color.White,
        primaryContainer = Green30,
        onPrimaryContainer = Color.White,
        inversePrimary = Green80,
        secondary = Blue40,
        onSecondary = Color.White,
        secondaryContainer = Blue20,
        onSecondaryContainer = Blue90,
        tertiary = Blue40,
        onTertiary = Color.White,
        tertiaryContainer = Blue90,
        onTertiaryContainer = Blue10,
        error = Red40,
        onError = Color.White,
        errorContainer = Red90,
        onErrorContainer = Red10,
        background = Color.White,
        onBackground = Grey10,
        surface = Color.White,
        onSurface = Grey10,
        inverseSurface = Grey20,
        inverseOnSurface = Grey95,
        surfaceVariant = GreenGrey90,
        onSurfaceVariant = GreenGrey30,
        outline = GreenGrey50,
    )

@Composable
fun HapptechTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
