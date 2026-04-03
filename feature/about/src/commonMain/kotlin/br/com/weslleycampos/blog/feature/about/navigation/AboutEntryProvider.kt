package br.com.weslleycampos.blog.feature.about.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import br.com.weslleycampos.blog.core.navigation.entries.about.AboutEntry
import br.com.weslleycampos.blog.core.navigation.utils.EntryProvider
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.core.annotation.Single

@Single(binds = [EntryProvider::class])
class AboutEntryProvider : EntryProvider {
    override fun serializerModule() = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(AboutEntry::class, AboutEntry.serializer())
        }
    }

    override fun entryBuilder(): EntryProviderScope<NavKey>.() -> Unit = { aboutEntryBuilder() }
}
