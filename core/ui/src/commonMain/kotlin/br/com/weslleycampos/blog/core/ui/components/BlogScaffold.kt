package br.com.weslleycampos.blog.core.ui.components

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BlogScaffold(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = BlogTheme.colors.layout.background
    ) {
    }
}

@Preview(name = "Theme Preview", showBackground = true)
@Composable
private fun BlogScaffoldPreview() {
    BlogTheme {
        BlogScaffold()
    }
}
