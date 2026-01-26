package extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.internal.extensions.stdlib.capitalized
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

// Hack to support version catalog
// https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
val Project.libs
    get() = extensions.getByType<LibrariesForLibs>()

val Project.blogPackage: String
    get() = "br.com.weslleycampos.blog"

val Project.resourcePackage: String
    get() {
        return buildString {
            append("br.com.weslleycampos.blog.")
            path.removePrefix(":").replace(":", ".").let { append(it) }
            append(".resources")
        }
    }

val Project.packageName: String
    get() {
        return buildString {
            append("br.com.weslleycampos.blog")
            append(path.replace(":", "."))
        }
    }

fun String.toResClassName(): String = split(":")
    .filter { it.isNotEmpty() }
    .joinToString("") { it.capitalized() } + "Res"

fun DependencyHandlerScope.debugImplementation(module: Any) {
    add("debugImplementation", module)
}

fun DependencyHandlerScope.detektPlugins(module: Any) {
    add("detektPlugins", module)
}

fun DependencyHandlerScope.lintChecks(module: Any) {
    add("lintChecks", module)
}

fun DependencyHandlerScope.kspCommonMainMetadata(module: Any) {
    add("kspCommonMainMetadata", module)
}

fun DependencyHandlerScope.kspAndroid(module: Any) {
    add("kspAndroid", module)
}

fun DependencyHandlerScope.kspJvm(module: Any) {
    add("kspJvm", module)
}

fun DependencyHandlerScope.kspWasmJs(module: Any) {
    add("kspWasmJs", module)
}
