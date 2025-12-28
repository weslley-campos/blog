package br.com.weslleycampos.blog.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

interface Navigator {
    val navBackStack: NavBackStack<NavKey>
    fun <T : NavKey> navigate(key: T)
    fun navigateUp()
}

@Single(binds = [Navigator::class])
class NavigatorImpl(@Provided startEntry: NavKey) : Navigator {
    override val navBackStack = NavBackStack(startEntry)

    override fun <T : NavKey> navigate(key: T) {
        navBackStack.add(key)
    }

    override fun navigateUp() {
        navBackStack.removeLastOrNull()
    }
}
