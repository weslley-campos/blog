package br.com.weslleycampos.blog.feature.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.weslleycampos.blog.core.navigation.entries.home.HomeEntry
import br.com.weslleycampos.blog.core.navigation.utils.EntryProvider
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.core.annotation.Single

@Single(binds = [EntryProvider::class])
class HomeEntryProvider : EntryProvider {
    override fun serializerModule() = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeEntry::class, HomeEntry.serializer())
        }
    }

    override fun entryBuilder(): EntryProviderScope<NavKey>.() -> Unit = { homeEntryBuilder() }
}
