package br.com.weslleycampos.blog

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

@Composable
fun App(
    navBackStack: NavBackStack<NavKey>,
    entryBuilders: List<EntryProviderScope<NavKey>.() -> Unit>
) {
    BlogTheme {
        NavDisplay(
            backStack = navBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entryBuilders.forEach { builder -> this.builder() }
            },
        )
    }
}
