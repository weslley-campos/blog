package br.com.weslleycampos.blog.core.ui.utils

import androidx.compose.material3.adaptive.Posture
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import br.com.weslleycampos.blog.core.ui.utils.WindowType.Compact
import br.com.weslleycampos.blog.core.ui.utils.WindowType.Expanded
import br.com.weslleycampos.blog.core.ui.utils.WindowType.Medium
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(
    ExperimentalTestApi::class,
    ExperimentalMaterial3WindowSizeClassApi::class
)
class ComposeExtKtTest {
    @Composable
    private fun createWindowAdaptiveInfo(width: Dp): WindowAdaptiveInfo {
        return WindowAdaptiveInfo(
            windowSizeClass = WindowSizeClass(width.value.toInt(), 0),
            windowPosture = Posture()
        )
    }

    @Test
    fun `calculateWindowType returns Compact for width at Compact breakpoint`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(600.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Compact, result)
        }
    }

    @Test
    fun `calculateWindowType returns Compact for width above Compact but below Medium`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(700.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Compact, result)
        }
    }

    @Test
    fun `calculateWindowType returns Medium for width at Medium breakpoint`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(840.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Medium, result)
        }
    }

    @Test
    fun `calculateWindowType returns Medium for width above Medium but below Expanded`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(1000.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Medium, result)
        }
    }

    @Test
    fun `calculateWindowType returns Expanded for width at Expanded breakpoint`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(1200.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Expanded, result)
        }
    }

    @Test
    fun `calculateWindowType returns Expanded for width above Expanded breakpoint`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(1400.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Expanded, result)
        }
    }

    @Test
    fun `calculateWindowType returns Compact for width below Compact breakpoint`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(500.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Compact, result)
        }
    }

    @Test
    fun `calculateWindowType returns Compact for very small width`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(300.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Compact, result)
        }
    }

    @Test
    fun `calculateWindowType returns Compact for edge case just below Compact`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(599.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Compact, result)
        }
    }

    @Test
    fun `calculateWindowType returns Compact for edge case just below Medium`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(839.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Compact, result)
        }
    }

    @Test
    fun `calculateWindowType returns Medium for edge case just below Expanded`() = runComposeUiTest {
        setContent {
            val windowInfo = createWindowAdaptiveInfo(1199.dp)
            val result = windowInfo.calculateWindowType()
            assertEquals(Medium, result)
        }
    }
}
