package br.com.weslleycampos.blog.feature.login.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.weslleycampos.blog.core.navigation.entries.login.LoginEntry
import br.com.weslleycampos.blog.core.navigation.utils.EntryProvider
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.core.annotation.Single

@Single(binds = [EntryProvider::class])
class LoginEntryProvider : EntryProvider {
    override fun serializerModule() = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(LoginEntry::class, LoginEntry.serializer())
        }
    }

    override fun entryBuilder(): EntryProviderScope<NavKey>.() -> Unit = { loginEntryBuilder() }
}
