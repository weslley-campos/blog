package br.com.weslleycampos.blog.feature.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.weslleycampos.blog.core.navigation.entries.home.HomeEntry
import br.com.weslleycampos.blog.feature.home.HomeScreen

fun EntryProviderScope<NavKey>.homeEntryBuilder() {
    entry<HomeEntry> {
        HomeScreen()
    }
}
