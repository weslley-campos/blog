package br.com.weslleycampos.blog

import br.com.weslleycampos.blog.core.common.CommonModule
import br.com.weslleycampos.blog.core.navigation.NavigationModule
import br.com.weslleycampos.blog.feature.about.AboutModule
import br.com.weslleycampos.blog.feature.home.HomeModule
import br.com.weslleycampos.blog.feature.login.LoginModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        CommonModule::class,
        NavigationModule::class,
        HomeModule::class,
        AboutModule::class,
        LoginModule::class,
    ]
)
@ComponentScan
class AppModule
