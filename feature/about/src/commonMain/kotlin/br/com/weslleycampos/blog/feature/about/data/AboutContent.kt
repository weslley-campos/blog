package br.com.weslleycampos.blog.feature.about.data

import br.com.weslleycampos.blog.core.ui.resources.CoreUiRes
import br.com.weslleycampos.blog.core.ui.resources.ic_android
import br.com.weslleycampos.blog.core.ui.resources.ic_app_store
import br.com.weslleycampos.blog.core.ui.resources.ic_compose
import br.com.weslleycampos.blog.core.ui.resources.ic_cpu
import br.com.weslleycampos.blog.core.ui.resources.ic_firebase
import br.com.weslleycampos.blog.core.ui.resources.ic_flask_conical
import br.com.weslleycampos.blog.core.ui.resources.ic_git
import br.com.weslleycampos.blog.core.ui.resources.ic_google_play
import br.com.weslleycampos.blog.core.ui.resources.ic_infinity
import br.com.weslleycampos.blog.core.ui.resources.ic_java
import br.com.weslleycampos.blog.core.ui.resources.ic_kanban
import br.com.weslleycampos.blog.core.ui.resources.ic_kmp
import br.com.weslleycampos.blog.core.ui.resources.ic_kotlin
import br.com.weslleycampos.blog.core.ui.resources.ic_ktor
import br.com.weslleycampos.blog.core.ui.resources.ic_layers
import br.com.weslleycampos.blog.core.ui.resources.ic_mongodb
import br.com.weslleycampos.blog.core.ui.resources.ic_monitor_check
import br.com.weslleycampos.blog.core.ui.resources.ic_network
import br.com.weslleycampos.blog.core.ui.resources.ic_node
import br.com.weslleycampos.blog.core.ui.resources.ic_plug
import br.com.weslleycampos.blog.core.ui.resources.ic_puzzle
import br.com.weslleycampos.blog.core.ui.resources.ic_repeat
import br.com.weslleycampos.blog.core.ui.resources.ic_server
import br.com.weslleycampos.blog.core.ui.resources.ic_syringe
import br.com.weslleycampos.blog.core.ui.resources.ic_target
import br.com.weslleycampos.blog.core.ui.resources.ic_typescript
import org.jetbrains.compose.resources.DrawableResource

/**
 * Static content for the About screen.
 *
 * Section labels (eyebrows, titles, button copy) live in `core/ui` strings.xml
 * and are resolved via `stringResource` at render time. The structured lists
 * here — experiences, tech badges, social links — keep their copy in Kotlin
 * because each entry is a stable record rather than user-facing chrome.
 *
 * Source of truth: `~/Library/Mobile Documents/com~apple~CloudDocs/Resume/`
 *  (`resume.md` for English, `curriculo.md` for Portuguese). Keep entries
 *  here in sync with those files.
 */

internal data class TechItem(
    val label: String,
    val icon: DrawableResource,
)

internal data class TechGroup(
    val label: String,
    val items: List<TechItem>,
)

internal data class Experience(
    val period: String,
    val company: String,
    val description: String,
)

internal enum class SocialLink(val label: String, val url: String) {
    Github("GitHub", "https://github.com/weslley-campos"),
    Linkedin("LinkedIn", "https://www.linkedin.com/in/weslley-campos/"),
    Email("Email", "mailto:weslley.campos@icloud.com"),
}

internal val techArsenal: List<TechGroup> = listOf(
    TechGroup(
        label = "Languages",
        items = listOf(
            TechItem("Kotlin", CoreUiRes.drawable.ic_kotlin),
            TechItem("KMP", CoreUiRes.drawable.ic_kmp),
            TechItem("Java", CoreUiRes.drawable.ic_java),
            TechItem("TypeScript", CoreUiRes.drawable.ic_typescript),
        ),
    ),
    TechGroup(
        label = "Android & mobile",
        items = listOf(
            TechItem("Android", CoreUiRes.drawable.ic_android),
            TechItem("Compose", CoreUiRes.drawable.ic_compose),
            TechItem("Play Store", CoreUiRes.drawable.ic_google_play),
            TechItem("App Store", CoreUiRes.drawable.ic_app_store),
        ),
    ),
    TechGroup(
        label = "Backend & data",
        items = listOf(
            TechItem("Ktor", CoreUiRes.drawable.ic_ktor),
            TechItem("Node.js", CoreUiRes.drawable.ic_node),
            TechItem("MongoDB", CoreUiRes.drawable.ic_mongodb),
        ),
    ),
    TechGroup(
        label = "Tools & cloud",
        items = listOf(
            TechItem("Firebase", CoreUiRes.drawable.ic_firebase),
            TechItem("Git", CoreUiRes.drawable.ic_git),
        ),
    ),
    TechGroup(
        label = "Practices",
        items = listOf(
            TechItem("MVP", CoreUiRes.drawable.ic_target),
            TechItem("MVVM", CoreUiRes.drawable.ic_layers),
            TechItem("Architecture", CoreUiRes.drawable.ic_network),
            TechItem("Design Patterns", CoreUiRes.drawable.ic_puzzle),
            TechItem("DI", CoreUiRes.drawable.ic_syringe),
            TechItem("REST APIs", CoreUiRes.drawable.ic_server),
            TechItem("Sockets", CoreUiRes.drawable.ic_plug),
            TechItem("Unit Testing", CoreUiRes.drawable.ic_flask_conical),
            TechItem("UI Testing", CoreUiRes.drawable.ic_monitor_check),
            TechItem("CI/CD", CoreUiRes.drawable.ic_infinity),
            TechItem("Agile", CoreUiRes.drawable.ic_repeat),
            TechItem("Kanban", CoreUiRes.drawable.ic_kanban),
            TechItem("Embedded", CoreUiRes.drawable.ic_cpu),
        ),
    ),
)

internal val experiences: List<Experience> = listOf(
    Experience(
        period = "Apr 2024 — Present",
        company = "SmartHome+ | Telus Digital",
        description = "Leading the migration of SmartHome+ business logic into a Kotlin Multiplatform " +
            "(KMP) library shared by both Android and iOS apps, enabling code reuse across platforms, " +
            "reducing development time, and improving consistency and maintainability. Additionally, " +
            "implemented dashboards and event monitoring features for tracking energy consumption, " +
            "helping users understand and reduce their energy usage. Integrated smart devices to enhance " +
            "home automation, streamline daily routines, and promote energy-efficient behaviors.",
    ),
    Experience(
        period = "Jul 2023 — Apr 2024",
        company = "Peloton | Thoughtworks",
        description = "Developed a diagnostic app to significantly reduce the company's costs associated " +
            "with daily service calls. Analyzed customer requirements and implemented functionalities " +
            "to collect data from device sensors, system logs, network activities, and user interactions.",
    ),
    Experience(
        period = "Sep 2022 — Jun 2023",
        company = "Invest Voyager | Thoughtworks",
        description = "Discontinued non-essential services, resulting in monthly savings of \$42,000 " +
            "for the company.",
    ),
    Experience(
        period = "Jan 2022 — Mar 2022",
        company = "Tectoy | Eldorado Institute",
        description = "Developed a facial recognition access control system for buildings and offices, " +
            "overcoming challenges of deploying on Android-based totems with large screens.",
    ),
    Experience(
        period = "Oct 2021 — Jan 2022",
        company = "HP | Eldorado Institute",
        description = "Created a diagnostic module proof of concept within the HP Smart application that " +
            "identifies and resolves potential errors in HP printers. The POC's success led to its " +
            "production release, now utilized by millions of users on the Google Play Store.",
    ),
    Experience(
        period = "Apr 2021 — Sep 2021",
        company = "Positivo | Eldorado Institute",
        description = "Led the development of an app store and contributed to the MDM system. Detected " +
            "malfunctioning payment machines and automated their replacement, resulting in significant " +
            "monthly cost savings. The app store further enhanced sellers' profitability by providing " +
            "optimized applications.",
    ),
    Experience(
        period = "Nov 2017 — Feb 2021",
        company = "Freelancer Android",
        description = "Worked on several Android apps: ZoeTropic, where ad integration increased revenue; " +
            "Saúde Web, focusing on maintenance and bug fixes; and Mobitraxx, where bug fixes and new " +
            "features resulted in higher user retention and reduced churn rate.",
    ),
)
