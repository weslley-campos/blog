package br.com.weslleycampos.blog.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.weslleycampos.blog.core.ui.constants.defaultLanguage
import br.com.weslleycampos.blog.core.ui.constants.portugueseLanguage
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.a11y_selected_language
import br.com.weslleycampos.blog.core.ui.resources.common_apply
import br.com.weslleycampos.blog.core.ui.resources.en_US
import br.com.weslleycampos.blog.core.ui.resources.language_selector_title
import br.com.weslleycampos.blog.core.ui.resources.pt_BR
import br.com.weslleycampos.blog.core.ui.theme.BlogTheme
import br.com.weslleycampos.blog.core.ui.theme.painter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

data class Language(val code: String, val name: StringResource, val flag: DrawableResource)

private val languagesAvailable: List<Language>
    @Composable get() = listOf(
        Language(defaultLanguage, CoreUiRes.string.en_US, BlogTheme.icons.USAFlag),
        Language(portugueseLanguage, CoreUiRes.string.pt_BR, BlogTheme.icons.BrazilFlag),
    )

@Composable
fun SelectLanguageDialog(
    onSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        val currentLanguage = BlogTheme.language.toLanguageTag()
        var selectedLanguage by remember { mutableStateOf(currentLanguage) }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = BlogTheme.colors.surfaceElevated
            ),
            modifier = modifier.padding(BlogTheme.spacing.xl)
                .widthIn(max = 320.dp)
        ) {
            Column(
                modifier = Modifier.padding(BlogTheme.spacing.lg)
            ) {
                Text(
                    text = stringResource(CoreUiRes.string.language_selector_title),
                    style = BlogTheme.typography.bodyLarge,
                    color = BlogTheme.colors.textPrimary,
                    modifier = Modifier.padding(vertical = BlogTheme.spacing.lg)
                )
                languagesAvailable.forEach { language ->
                    LanguageItem(
                        language = language,
                        isSelected = language.code == selectedLanguage,
                        onClick = { selectedLanguage = it }
                    )
                    Spacer(modifier = Modifier.size(BlogTheme.spacing.xs))
                }
                Button(
                    onClick = {
                        onSelected(selectedLanguage)
                        onDismiss()
                    },
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = BlogTheme.spacing.lg),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BlogTheme.colors.brand,
                    )
                ) {
                    Text(
                        text = stringResource(CoreUiRes.string.common_apply),
                        color = BlogTheme.colors.textInverse
                    )
                }
            }
        }
    }
}

@Composable
fun LanguageItem(
    language: Language,
    isSelected: Boolean,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clip(BlogTheme.shapes.medium)
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 1.dp,
                        color = BlogTheme.colors.brand,
                        shape = BlogTheme.shapes.medium
                    ).background(
                        color = BlogTheme.colors.brand.copy(alpha = 0.1f),
                    )
                } else {
                    Modifier
                }
            )
            .clickable(onClick = { onClick(language.code) })
            .padding(
                vertical = BlogTheme.spacing.sm,
                horizontal = BlogTheme.spacing.lg
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BlogTheme.spacing.md),
    ) {
        Image(
            painter = language.flag.painter,
            contentDescription = "",
        )
        Text(
            text = stringResource(language.name),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            modifier = Modifier.weight(1f),
            color = BlogTheme.colors.textPrimary
        )

        AnimatedVisibility(
            visible = isSelected,
            enter = scaleIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessHigh,
                )
            ),
            exit = scaleOut()
        ) {
            Surface(
                shape = CircleShape,
                color = BlogTheme.colors.brand,
                modifier = Modifier.size(BlogTheme.sizes.icon.small)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(CoreUiRes.string.a11y_selected_language),
                    tint = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LanguageSelectDialogPreview() {
    var selectedLanguage by remember { mutableStateOf(defaultLanguage) }

    BlogTheme(language = Locale(languageTag = selectedLanguage)) {
        SelectLanguageDialog(
            onSelected = { selectedLanguage = it },
            onDismiss = {}
        )
    }
}
