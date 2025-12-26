package br.com.weslleycampos.blog

import br.com.weslleycampos.blog.core.navigation.NavigationModule
import br.com.weslleycampos.feature.home.HomeModule
import org.koin.core.annotation.Module

@Module(
    includes = [
        NavigationModule::class,
        HomeModule::class
    ]
)
class AppModule
