package br.com.weslleycampos.blog

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.savedstate.serialization.SavedStateConfiguration
import br.com.weslleycampos.blog.core.common.di.BlogKoinApp
import br.com.weslleycampos.blog.core.navigation.Navigator
import br.com.weslleycampos.blog.core.navigation.entries.about.AboutEntry
import br.com.weslleycampos.blog.core.navigation.utils.EntriesAggregator
import br.com.weslleycampos.blog.core.navigation.utils.bindNavBackStack
import kotlinx.serialization.modules.SerializersModule
import org.koin.compose.koinInject
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin
import java.awt.Dimension

fun main() = application {
    BlogKoinApp.startKoin {
        printLogger(level = Level.DEBUG)
        modules(AppModule().module)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Blog",
    ) {
        window.minimumSize = Dimension(320, 700)
        val entries = koinInject<EntriesAggregator>().entries
        val navigator = koinInject<Navigator> { parametersOf(AboutEntry) }

        val config = SavedStateConfiguration {
            serializersModule = SerializersModule {
                entries.forEach { entry -> include(entry.serializerModule()) }
            }
        }

        App(
            navBackStack = bindNavBackStack(config, navigator.navBackStack),
            entryBuilders = entries.map { it.entryBuilder() },
        )
    }
}
