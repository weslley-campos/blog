package br.com.weslleycampos.blog.core.ui.utils

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize.Compact
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize.Expanded
import br.com.weslleycampos.blog.core.ui.utils.ScreenSize.Medium

/**
 * Calculates the [ScreenSize] based on the current [WindowAdaptiveInfo].
 *
 * This function determines the window type (Compact, Medium, or Expanded)
 * by checking the width of the window against predefined breakpoints.
 *
 * - If the width is at least the Compact breakpoint, it's considered Compact.
 * - If the width is at least the Medium breakpoint, it's considered Medium.
 * - Otherwise, it's considered Expanded.
 *
 * @receiver The [WindowAdaptiveInfo] providing information about the current window size.
 * @return The calculated [ScreenSize] (Compact, Medium, or Expanded).
 */
@Composable
fun WindowAdaptiveInfo.calculateScreenSize(): ScreenSize {
    return when {
        !windowSizeClass.isWidthAtLeastBreakpoint(Compact.size) -> Compact
        !windowSizeClass.isWidthAtLeastBreakpoint(Medium.size) -> Medium
        else -> Expanded
    }
}
