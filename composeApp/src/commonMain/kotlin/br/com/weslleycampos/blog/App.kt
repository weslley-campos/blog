package br.com.weslleycampos.blog

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.navigation.BlogNavHost

@Composable
fun App(
    startDestination: Any,
    navController: NavHostController
) {
    BlogTheme {
        BlogNavHost(
            startDestination = startDestination,
            navController = navController
        )
    }
}
