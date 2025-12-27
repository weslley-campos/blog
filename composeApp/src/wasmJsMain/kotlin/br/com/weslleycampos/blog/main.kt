package br.com.weslleycampos.blog

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.savedstate.serialization.SavedStateConfiguration
import br.com.weslleycampos.blog.core.common.di.BlogKoinApp
import br.com.weslleycampos.blog.core.navigation.Navigator
import br.com.weslleycampos.blog.core.navigation.entries.home.HomeEntry
import br.com.weslleycampos.blog.core.navigation.utils.EntryProvider
import br.com.weslleycampos.blog.core.navigation.utils.bindNavBackStack
import kotlinx.browser.document
import kotlinx.serialization.modules.SerializersModule
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    BlogKoinApp.startKoin {
        printLogger(level = Level.DEBUG)
        modules(AppModule().module)
    }

    ComposeViewport(viewportContainer = document.body!!) {
        val entries = getKoin().getAll<EntryProvider>()
        val navigator = koinInject<Navigator> { parametersOf(HomeEntry) }

        val config = SavedStateConfiguration {
            serializersModule = SerializersModule {
                entries.forEach { entry -> include(entry.serializerModule()) }
            }
        }

        App(
            navBackStack = bindNavBackStack(config, navigator.navBackStack),
            entryBuilders = entries.map { it.entryBuilder() }
        )
    }
}
