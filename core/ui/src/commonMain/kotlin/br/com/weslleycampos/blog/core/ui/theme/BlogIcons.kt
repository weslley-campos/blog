package br.com.weslleycampos.blog.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.painter.Painter
import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.ic_brazil_flag
import br.com.weslleycampos.blog.core.ui.resources.ic_cup_coffee
import br.com.weslleycampos.blog.core.ui.resources.ic_home
import br.com.weslleycampos.blog.core.ui.resources.ic_home_outline
import br.com.weslleycampos.blog.core.ui.resources.ic_menu
import br.com.weslleycampos.blog.core.ui.resources.ic_moon
import br.com.weslleycampos.blog.core.ui.resources.ic_newspaper
import br.com.weslleycampos.blog.core.ui.resources.ic_newspaper_outline
import br.com.weslleycampos.blog.core.ui.resources.ic_post
import br.com.weslleycampos.blog.core.ui.resources.ic_post_outline
import br.com.weslleycampos.blog.core.ui.resources.ic_sun
import br.com.weslleycampos.blog.core.ui.resources.ic_translate
import br.com.weslleycampos.blog.core.ui.resources.ic_usa_flag
import br.com.weslleycampos.blog.core.ui.resources.ic_user
import br.com.weslleycampos.blog.core.ui.resources.ic_user_outline
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

object BlogIcons {
    val CupCoffee: DrawableResource = CoreUiRes.drawable.ic_cup_coffee
    val Home: DrawableResource = CoreUiRes.drawable.ic_home
    val HomeOutline: DrawableResource = CoreUiRes.drawable.ic_home_outline
    val Post: DrawableResource = CoreUiRes.drawable.ic_post
    val PostOutline: DrawableResource = CoreUiRes.drawable.ic_post_outline
    val User: DrawableResource = CoreUiRes.drawable.ic_user
    val UserOutline: DrawableResource = CoreUiRes.drawable.ic_user_outline
    val Newspaper: DrawableResource = CoreUiRes.drawable.ic_newspaper
    val NewspaperOutline: DrawableResource = CoreUiRes.drawable.ic_newspaper_outline
    val Menu: DrawableResource = CoreUiRes.drawable.ic_menu
    val Translate: DrawableResource = CoreUiRes.drawable.ic_translate
    val Moon: DrawableResource = CoreUiRes.drawable.ic_moon
    val Sun: DrawableResource = CoreUiRes.drawable.ic_sun

    // Flags
    val BrazilFlag: DrawableResource = CoreUiRes.drawable.ic_brazil_flag
    val USAFlag: DrawableResource = CoreUiRes.drawable.ic_usa_flag
}

val DrawableResource.painter: Painter
    @Composable get() = painterResource(this)

val LocalBlogIcons = staticCompositionLocalOf<BlogIcons> {
    error("No BlogIcons provided")
}
