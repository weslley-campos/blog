package br.com.weslleycampos.blog

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import br.com.weslleycampos.blog.core.ui.components.BlogTopBar
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    navBackStack: NavBackStack<NavKey>,
    entryBuilders: List<EntryProviderScope<NavKey>.() -> Unit>,
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = koinViewModel()
) {
    val appState by viewModel.appState.collectAsStateWithLifecycle()

    BlogTheme(
        isDarkMode = appState.isDarkThemeEnabled ?: isSystemInDarkTheme(),
        language = appState.blogLanguage,
    ) {
        Column(
            modifier = modifier.fillMaxSize()
                .requiredSizeIn(minWidth = 320.dp, minHeight = 70.dp)
        ) {
            BlogTopBar(
                onThemeToggle = viewModel::setDarkThemeEnabled,
                onSelectLanguage = viewModel::setLanguage,
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
