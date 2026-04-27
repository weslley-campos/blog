package br.com.weslleycampos.blog.feature.about.data

/**
 * Static content for the About screen.
 *
 * Section labels (eyebrows, titles, button copy) live in `core/ui` strings.xml
 * and are resolved via `stringResource` at render time. The structured lists
 * here — experiences, tech badges, social links — keep their copy in Kotlin
 * because each entry is a stable record rather than user-facing chrome. Add
 * locale-specific variants later by promoting these fields to `StringResource`.
 */

internal data class TechItem(
    val label: String,
    val initials: String,
)

internal data class Experience(
    val period: String,
    val role: String,
    val company: String,
    val description: String,
)

internal enum class SocialLink(val label: String, val url: String) {
    Github("GitHub", "https://github.com/weslley-telus"),
    Linkedin("LinkedIn", "https://www.linkedin.com/in/weslley-campos/"),
    Email("Email", "mailto:weslley.campos@icloud.com"),
}

internal val techArsenal: List<TechItem> = listOf(
    TechItem("Kotlin", "K"),
    TechItem("KMP", "KMP"),
    TechItem("Compose", "C"),
    TechItem("Android", "AND"),
    TechItem("Java", "J"),
    TechItem("Coroutines", "CO"),
    TechItem("Ktor", "KT"),
    TechItem("Room", "R"),
    TechItem("Koin", "KO"),
    TechItem("Hilt", "H"),
    TechItem("Gradle", "G"),
    TechItem("Jetpack", "JP"),
)

internal val experiences: List<Experience> = listOf(
    Experience(
        period = "Apr 2024 — Present",
        role = "Senior Android Engineer",
        company = "SmartHome+ · Telus Digital",
        description = "Leading Android architecture for the SmartHome+ app, focusing on Compose adoption, " +
            "performance, and connected-device experiences across Telus' smart-home portfolio.",
    ),
    Experience(
        period = "Jul 2023 — Apr 2024",
        role = "Senior Android Engineer",
        company = "Peloton · Thoughtworks",
        description = "Shipped fitness experiences for Peloton's Android app: live class playback, " +
            "workout history, and member engagement surfaces with rigorous quality gates.",
    ),
    Experience(
        period = "Sep 2022 — Jun 2023",
        role = "Senior Android Engineer",
        company = "Invest Voyager · Thoughtworks",
        description = "Built crypto-investing flows for Invest Voyager's Android client, partnering with " +
            "compliance and trading teams on secure, low-latency money movement.",
    ),
    Experience(
        period = "2021 — 2022",
        role = "Android Engineer",
        company = "Eldorado Institute · Tectoy / HP / Positivo",
        description = "Embedded with three OEM teams to deliver Android system features and user-facing " +
            "apps under tight certification and ship deadlines.",
    ),
    Experience(
        period = "Nov 2017 — Feb 2021",
        role = "Freelance Android Developer",
        company = "Independent",
        description = "Designed and built Android apps end-to-end for a range of clients — from MVPs to " +
            "production rollouts — across consumer, fintech, and IoT domains.",
    ),
)
