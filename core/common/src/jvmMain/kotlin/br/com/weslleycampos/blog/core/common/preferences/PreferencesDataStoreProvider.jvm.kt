package br.com.weslleycampos.blog.core.common.preferences

import org.jetbrains.skiko.OS
import org.koin.core.annotation.Single
import org.koin.core.scope.Scope
import java.io.File

@Single
actual fun providePreferencesDataStore(scope: Scope) = createPreferencesDataStore {
    val appDir = when (currentOS) {
        OS.MacOS -> "${System.getProperty("user.home")}/Library/Application Support/Blog"
        OS.Windows -> "${System.getenv("APPDATA") ?: System.getProperty("user.home")}/Blog"
        OS.Linux -> "${System.getProperty("user.home")}/.blog"
        else -> System.getProperty("java.io.tmpdir")
    }

    val directory = File(appDir)
    if (!directory.exists()) {
        directory.mkdirs()
    }

    File(appDir, preferencesDataStoreFileName).absolutePath
}

private val currentOS: OS
    get() {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> OS.Windows
            os.contains("nux") || os.contains("nix") -> OS.Linux
            os.contains("mac") || os.contains("darwin") -> OS.MacOS
            else -> OS.Unknown
        }
    }