package br.com.weslleycampos.blog.core.ui.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colorScheme] in preference to [BlogTheme.colors].
 */

val debugColorScheme: ColorScheme
    get() {
        val debugColor = Color.Magenta

        return ColorScheme(
            primary = debugColor,
            onPrimary = debugColor,
            primaryContainer = debugColor,
            onPrimaryContainer = debugColor,
            inversePrimary = debugColor,
            secondary = debugColor,
            onSecondary = debugColor,
            secondaryContainer = debugColor,
            onSecondaryContainer = debugColor,
            tertiary = debugColor,
            onTertiary = debugColor,
            tertiaryContainer = debugColor,
            onTertiaryContainer = debugColor,
            background = debugColor,
            onBackground = debugColor,
            surface = debugColor,
            onSurface = debugColor,
            surfaceVariant = debugColor,
            onSurfaceVariant = debugColor,
            surfaceTint = debugColor,
            inverseSurface = debugColor,
            inverseOnSurface = debugColor,
            error = debugColor,
            onError = debugColor,
            errorContainer = debugColor,
            onErrorContainer = debugColor,
            outline = debugColor,
            outlineVariant = debugColor,
            scrim = debugColor,
            surfaceBright = debugColor,
            surfaceDim = debugColor,
            surfaceContainer = debugColor,
            surfaceContainerHigh = debugColor,
            surfaceContainerHighest = debugColor,
            surfaceContainerLow = debugColor,
            surfaceContainerLowest = debugColor,
            primaryFixed = debugColor,
            primaryFixedDim = debugColor,
            onPrimaryFixed = debugColor,
            onPrimaryFixedVariant = debugColor,
            secondaryFixed = debugColor,
            secondaryFixedDim = debugColor,
            onSecondaryFixed = debugColor,
            onSecondaryFixedVariant = debugColor,
            tertiaryFixed = debugColor,
            tertiaryFixedDim = debugColor,
            onTertiaryFixed = debugColor,
            onTertiaryFixedVariant = debugColor,
        )
    }
