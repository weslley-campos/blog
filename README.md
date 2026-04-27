# Blog

Personal blog of [Weslley Campos](https://github.com/weslley-campos). Built with Kotlin Multiplatform and Compose Multiplatform — one Kotlin codebase running on **web (Wasm)**, **Desktop (JVM)**, **Android**, and **iOS**.

The Wasm target is the first to ship (served via nginx in Docker). Desktop, Android, and iOS will roll out incrementally — the multiplatform stack is in place from day one, the deployments arrive over time.

## Stack

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.20-7F52FF?logo=kotlin&logoColor=white&style=flat-square)](https://kotlinlang.org/)
[![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.11.0--beta03-4285F4?logo=jetpackcompose&logoColor=white&style=flat-square)](https://www.jetbrains.com/compose-multiplatform/)
[![Navigation 3](https://img.shields.io/badge/Navigation-3-3DDC84?style=flat-square)](https://developer.android.com/jetpack/androidx/releases/navigation3)
[![Koin](https://img.shields.io/badge/Koin-4-3880FF?logo=koin&logoColor=white&style=flat-square)](https://insert-koin.io/)
[![DataStore](https://img.shields.io/badge/DataStore-1.3-3DDC84?logo=android&logoColor=white&style=flat-square)](https://developer.android.com/jetpack/androidx/releases/datastore)
[![Detekt](https://img.shields.io/badge/Detekt-1.23.8-7E57C2?style=flat-square)](https://detekt.dev/)

### Targets

[![WebAssembly](https://img.shields.io/badge/WebAssembly-654FF0?logo=webassembly&logoColor=white&style=for-the-badge)](https://webassembly.org/)
[![JVM Desktop](https://img.shields.io/badge/JVM_Desktop-437291?logo=openjdk&logoColor=white&style=for-the-badge)](https://www.jetbrains.com/compose-multiplatform/)
[![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white&style=for-the-badge)](https://developer.android.com/)
[![iOS](https://img.shields.io/badge/iOS-000000?logo=ios&logoColor=white&style=for-the-badge)](https://developer.apple.com/ios/)

## Module graph

```mermaid
graph TD
    composeApp[composeApp]
    home[feature/home]
    about[feature/about]
    ui[core/ui]
    common[core/common]
    nav[core/navigation]
    markdown[core/markdown]

    composeApp --> home
    composeApp --> about
    composeApp --> ui
    composeApp --> common
    composeApp --> nav

    home --> ui
    home --> nav
    home --> markdown

    about --> ui
    about --> nav

    classDef app fill:#7F52FF,stroke:#37BCFD,color:#fff,stroke-width:2px
    classDef feature fill:#22D3EE,stroke:#7F52FF,color:#000,stroke-width:2px
    classDef core fill:#F1F5F9,stroke:#475569,color:#000
    class composeApp app
    class home,about feature
    class ui,common,nav,markdown core
```
