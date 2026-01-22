package br.com.weslleycampos.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import br.com.weslleycampos.blog.core.ui.components.BlogTopBar
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

@Composable
fun App(
    navBackStack: NavBackStack<NavKey>,
    entryBuilders: List<EntryProviderScope<NavKey>.() -> Unit>,
    modifier: Modifier = Modifier
) {
    var isDarkThemeEnabled by remember { mutableStateOf(false) }
    var blogLanguage by remember { mutableStateOf(Locale("en-US")) }

    BlogTheme(
        isDarkMode = isDarkThemeEnabled,
        language = blogLanguage,
    ) {
        Column(
            modifier = modifier.fillMaxSize()
                .requiredSizeIn(minWidth = 320.dp, minHeight = 70.dp)
        ) {
            BlogTopBar(
                onThemeToggle = { isDarkThemeEnabled = !isDarkThemeEnabled },
                onSelectLanguage = { blogLanguage = it },
            )
            NavDisplay(
                backStack = navBackStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {
                    entryBuilders.forEach { builder -> this.builder() }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
