package br.com.weslleycampos.blog

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.savedstate.serialization.SavedStateConfiguration
import br.com.weslleycampos.blog.core.common.di.BlogKoinApp
import br.com.weslleycampos.blog.core.navigation.Navigator
import br.com.weslleycampos.blog.core.navigation.entries.home.HomeEntry
import br.com.weslleycampos.blog.core.navigation.utils.EntryProvider
import br.com.weslleycampos.blog.core.navigation.utils.bindNavBackStack
import kotlinx.serialization.modules.SerializersModule
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin

fun main() = application {
    BlogKoinApp.startKoin {
        printLogger(level = Level.DEBUG)
        modules(AppModule().module)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Blog",
    ) {
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
