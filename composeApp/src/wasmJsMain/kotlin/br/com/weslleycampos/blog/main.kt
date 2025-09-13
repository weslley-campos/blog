package br.com.weslleycampos.blog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.NavHostController
import androidx.navigation.bindToBrowserNavigation
import androidx.navigation.compose.rememberNavController
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
        val navController = rememberNavController()

        EnableBrowserHistory(navController = navController)
        App(
            startDestination = "",
            navController = navController
        )
    }
}

@OptIn(ExperimentalBrowserHistoryApi::class)
@Composable
private fun EnableBrowserHistory(navController: NavHostController) {
    LaunchedEffect(Unit) {
        navController.bindToBrowserNavigation()
    }
}
