package br.com.weslleycampos.blog

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import br.com.weslleycampos.core.common.di.BlogKoinApp
import kotlinx.browser.document
import org.koin.core.logger.Level
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    BlogKoinApp.startKoin {
        printLogger(level = Level.DEBUG)
        modules(AppModule().module)
    }
    ComposeViewport(viewportContainer = document.body!!) {
        App()
    }
}
