package br.com.weslleycampos.blog.core.ui.utils

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import br.com.weslleycampos.blog.core.ui.utils.WindowType.Compact
import br.com.weslleycampos.blog.core.ui.utils.WindowType.Expanded
import br.com.weslleycampos.blog.core.ui.utils.WindowType.Medium

/**
 * Calculates the [WindowType] based on the current [WindowAdaptiveInfo].
 *
 * This function determines the window type (Compact, Medium, or Expanded)
 * by checking the width of the window against predefined breakpoints.
 *
 * - If the width is at least the Compact breakpoint, it's considered Compact.
 * - If the width is at least the Medium breakpoint, it's considered Medium.
 * - Otherwise, it's considered Expanded.
 *
 * @receiver The [WindowAdaptiveInfo] providing information about the current window size.
 * @return The calculated [WindowType] (Compact, Medium, or Expanded).
 */
@Composable
fun WindowAdaptiveInfo.calculateWindowType(): WindowType {
    return when {
        windowSizeClass.isWidthAtLeastBreakpoint(Expanded.size) -> Expanded
        windowSizeClass.isWidthAtLeastBreakpoint(Medium.size) -> Medium
        windowSizeClass.isWidthAtLeastBreakpoint(Compact.size) -> Compact
        else -> Compact
    }
}
