package br.com.weslleycampos.blog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme

@Composable
fun App() {
    BlogTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = BlogTheme.colors.layout.background)
        ) {
            Text(
                text = "Hello, Working in progress!",
                modifier = Modifier.align(Alignment.Center),
                color = BlogTheme.colors.text.onBackground
            )
        }
    }
}
