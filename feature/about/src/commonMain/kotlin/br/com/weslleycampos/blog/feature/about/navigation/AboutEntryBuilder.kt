package br.com.weslleycampos.blog.feature.about.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.weslleycampos.blog.core.navigation.entries.about.AboutEntry
import br.com.weslleycampos.blog.feature.about.AboutScreen

fun EntryProviderScope<NavKey>.aboutEntryBuilder() {
    entry<AboutEntry> {
        AboutScreen()
    }
}
