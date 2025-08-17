package extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

// Hack to support version catalog
// https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
val Project.libs
    get() = extensions.getByType<LibrariesForLibs>()

fun DependencyHandlerScope.detektPlugins(module: Any) {
    add("detektPlugins", module)
}