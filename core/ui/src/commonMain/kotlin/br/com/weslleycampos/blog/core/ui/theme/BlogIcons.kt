package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.painter.Painter
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.ic_arrow_right
import br.com.weslleycampos.blog.core.ui.resources.ic_brazil_flag
import br.com.weslleycampos.blog.core.ui.resources.ic_briefcase
import br.com.weslleycampos.blog.core.ui.resources.ic_chevron_down
import br.com.weslleycampos.blog.core.ui.resources.ic_chevron_right
import br.com.weslleycampos.blog.core.ui.resources.ic_close
import br.com.weslleycampos.blog.core.ui.resources.ic_code
import br.com.weslleycampos.blog.core.ui.resources.ic_cup_coffee
import br.com.weslleycampos.blog.core.ui.resources.ic_download
import br.com.weslleycampos.blog.core.ui.resources.ic_external_link
import br.com.weslleycampos.blog.core.ui.resources.ic_file_text
import br.com.weslleycampos.blog.core.ui.resources.ic_github
import br.com.weslleycampos.blog.core.ui.resources.ic_globe
import br.com.weslleycampos.blog.core.ui.resources.ic_home
import br.com.weslleycampos.blog.core.ui.resources.ic_home_outline
import br.com.weslleycampos.blog.core.ui.resources.ic_linkedin
import br.com.weslleycampos.blog.core.ui.resources.ic_mail
import br.com.weslleycampos.blog.core.ui.resources.ic_menu
import br.com.weslleycampos.blog.core.ui.resources.ic_moon
import br.com.weslleycampos.blog.core.ui.resources.ic_newspaper
import br.com.weslleycampos.blog.core.ui.resources.ic_newspaper_outline
import br.com.weslleycampos.blog.core.ui.resources.ic_post
import br.com.weslleycampos.blog.core.ui.resources.ic_post_outline
import br.com.weslleycampos.blog.core.ui.resources.ic_search
import br.com.weslleycampos.blog.core.ui.resources.ic_sun
import br.com.weslleycampos.blog.core.ui.resources.ic_translate
import br.com.weslleycampos.blog.core.ui.resources.ic_usa_flag
import br.com.weslleycampos.blog.core.ui.resources.ic_user
import br.com.weslleycampos.blog.core.ui.resources.ic_user_outline
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

object BlogIcons {
    // Navigation
    val Home: DrawableResource = CoreUiRes.drawable.ic_home
    val HomeOutline: DrawableResource = CoreUiRes.drawable.ic_home_outline
    val Menu: DrawableResource = CoreUiRes.drawable.ic_menu
    val Close: DrawableResource = CoreUiRes.drawable.ic_close
    val Search: DrawableResource = CoreUiRes.drawable.ic_search
    val ArrowRight: DrawableResource = CoreUiRes.drawable.ic_arrow_right
    val ChevronRight: DrawableResource = CoreUiRes.drawable.ic_chevron_right
    val ChevronDown: DrawableResource = CoreUiRes.drawable.ic_chevron_down

    // Appearance
    val Sun: DrawableResource = CoreUiRes.drawable.ic_sun
    val Moon: DrawableResource = CoreUiRes.drawable.ic_moon

    // Locale
    val Globe: DrawableResource = CoreUiRes.drawable.ic_globe
    val Translate: DrawableResource = CoreUiRes.drawable.ic_translate
    val BrazilFlag: DrawableResource = CoreUiRes.drawable.ic_brazil_flag
    val USAFlag: DrawableResource = CoreUiRes.drawable.ic_usa_flag

    // Content
    val Post: DrawableResource = CoreUiRes.drawable.ic_post
    val PostOutline: DrawableResource = CoreUiRes.drawable.ic_post_outline
    val Newspaper: DrawableResource = CoreUiRes.drawable.ic_newspaper
    val NewspaperOutline: DrawableResource = CoreUiRes.drawable.ic_newspaper_outline
    val FileText: DrawableResource = CoreUiRes.drawable.ic_file_text
    val Code: DrawableResource = CoreUiRes.drawable.ic_code
    val Briefcase: DrawableResource = CoreUiRes.drawable.ic_briefcase

    // People
    val User: DrawableResource = CoreUiRes.drawable.ic_user
    val UserOutline: DrawableResource = CoreUiRes.drawable.ic_user_outline

    // Action
    val Download: DrawableResource = CoreUiRes.drawable.ic_download
    val Mail: DrawableResource = CoreUiRes.drawable.ic_mail
    val ExternalLink: DrawableResource = CoreUiRes.drawable.ic_external_link

    // Social
    val Github: DrawableResource = CoreUiRes.drawable.ic_github
    val Linkedin: DrawableResource = CoreUiRes.drawable.ic_linkedin

    // Decorative
    val CupCoffee: DrawableResource = CoreUiRes.drawable.ic_cup_coffee
}

val DrawableResource.painter: Painter
    @Composable get() = painterResource(this)

val LocalBlogIcons = staticCompositionLocalOf<BlogIcons> {
    error("No BlogIcons provided")
}
