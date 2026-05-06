package br.com.weslleycampos.blog.feature.login.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.weslleycampos.blog.core.navigation.entries.login.LoginEntry
import br.com.weslleycampos.blog.feature.login.LoginScreen

fun EntryProviderScope<NavKey>.loginEntryBuilder() {
    entry<LoginEntry> {
        LoginScreen()
    }
}
