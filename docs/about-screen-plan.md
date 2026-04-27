# About screen — implementation plan

Source design: [Figma — Weslley Campos Blog Design System, ✨ About page](https://www.figma.com/design/htQf6z8niHfAn0h69lo8lA/Weslley-Campos-%E2%80%94-Blog-Design-System?node-id=55-6).

The page contains 6 frames in a 2×3 grid (Mobile / Tablet / Desktop × Dark / Light):

| Frame | nodeId | Size |
|---|---|---|
| About — Mobile / Dark | `61:2` | 375×1836 |
| About — Tablet / Dark | `61:112` | 768×2156 |
| About — Desktop / Dark | `61:248` | 1440×1956 |
| About — Mobile / Light | `61:386` | 375×1836 |
| About — Tablet / Light | `61:496` | 768×2156 |
| About — Desktop / Light | `61:632` | 1440×1956 |

Resume sources used as content reference:
- EN: `/Users/weslleycampos/Library/Mobile Documents/com~apple~CloudDocs/Resume/resume.md`
- PT: `/Users/weslleycampos/Library/Mobile Documents/com~apple~CloudDocs/Resume/curriculo.md`

---

## 1. Context audit (current project state)

| Area | Status |
|---|---|
| `feature/about` module | Scaffolded (commit `a530e21`). `AboutScreen.kt` is a "About Screen" placeholder Text. Koin module + `AboutEntry` + `AboutEntryProvider` already wired. |
| Theme tokens | Full semantic system in place (commit `ce24d18`): `BlogColors`, `BlogTypography`, `BlogSpacing`, `BlogShapes`, `BlogSizes`, `BlogGradients`, `BlogIcons`. Both `DarkColorPalette` and `LightColorPalette` populated. |
| Compose resources | `core/ui/src/commonMain/composeResources/values{,-pt-rBR}/strings.xml` exists with `a11y_*`, `app_name`, `author_name`, `language_*`. Pattern is established — extend it. |
| Adaptive layout | `LocalScreenSize` enum (`Compact`/`Medium`/`Expanded` at 600/840/1200 dp) + `BlogTopBar` already branches on it. Same pattern applies to `AboutScreen`. |
| Fonts loaded | Inter (all weights), Merriweather, JetBrains Mono — already in `core/ui/composeResources/font/`. |
| Module category | `feature/about` follows the standard `feature/*` convention (KMP + Compose + Koin + Detekt plugins). No additional Gradle work needed. |

Existing `AboutScreen.kt` body to be replaced:
```kotlin
// feature/about/src/commonMain/kotlin/.../AboutScreen.kt — current placeholder
Column(
    modifier = modifier.fillMaxSize().background(BlogTheme.colors.background),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Text(text = "About Screen")
}
```

---

## 2. Design ↔ token gap analysis

Comparing Figma variables (Mobile/Dark frame `61:2`) against `BlogColors.kt`:

| Figma token | Design hex | Code mapping | Status |
|---|---|---|---|
| `surface/background` | `#060E20` | `background` (Deep6) | ✅ exact |
| `surface/surface` | `#091328` | `surfaceNav` (Deep5) | ⚠️ same hex but used as **section bg** in design, not nav. `surfaceElevated` is Deep3 (`#0F1930`) — different. |
| `surface/container-high` | `#192540` | `interactiveSecondaryFill` (Deep1) | ⚠️ correct hex, but no semantic alias for "container surface" exists. |
| `text/primary` | `#DEE5FF` | `textPrimary` | ✅ exact |
| `text/secondary` | `#7A8BAA` | `textSecondary` | ✅ exact |
| `text/muted` | `#4A5A7A` | `textMuted` | ✅ exact |
| `text/link` | `#818CF8` | `textLink` (Shadow5) | ✅ exact |
| `code/function` | `#53DDFC` | not mapped | ❌ closest is `Ocean3` (`#67E8F9`) or `Ocean4` (`#22D3EE`). Used in Figma for eyebrow color and gradient endpoints. |
| `code/comment` | `#64748B` | `SyntaxComment` | ✅ exact |
| `code/background` | `#000000` | `codeBackground` (Deep7) | ✅ exact |
| `interactive/primary-pressed` | `#6366F1` | Shadow6 | ✅ exact |
| `interactive/accent-hover` | `#22D3EE` | Ocean4 | ✅ exact |
| Gradient `link → code/function` | `#818CF8 → #53DDFC` | `gradientBrand` = `Shadow5 → Ocean4` | ⚠️ endpoints differ slightly (Ocean4 = `#22D3EE` vs Figma `#53DDFC`). |

**Conclusion:** Design system is ~85% aligned. Five issues to resolve before building the screen — derived from the `design-system` skill's three-layer principle ("never inline raw hex in the semantic/component layers — always reference primitives"):

1. **Hex-leak cleanup (blocker).** `DarkColorPalette` and `LightColorPalette` currently inline raw `Color(0xFF...)` calls in ~12 places (textPrimary lavender, steel-blue text tones, status text, status accents, tag colors, `navSlate`). Promote them to new primitives so the semantic layer is a pure mapping over Layer 1.
2. **Cyan `#53DDFC` decision.** Use existing `Ocean4` (`#22D3EE`, ~2% off) — token-compliant. Drop the "or inline the exact hex" branch entirely. If exact match becomes required later, add a new primitive `Ocean3b = Color(0xFF53DDFC)`; never reach for raw hex inside a component or palette.
3. **Add `surfaceSection` semantic token.** Dark = `Deep5`, Light = `Neutral1` (TBC against light frames). Used for alternating section backgrounds (Tech Arsenal etc.) instead of overloading `surfaceNav`.
4. **Split `interactiveSecondaryFill` from `surfaceContainerHigh`.** One semantic token per *purpose*, even when both currently resolve to the same primitive (`Deep1` / `Neutral2`). Lets the container surface diverge from the button fill later without component churn. Promote from "optional" to required.
5. **Add per-component state spec tables.** Phase 4 introduces `BlogPrimaryButton`, `BlogSecondaryButton`, `SectionHeader`, `GradientText`. Each must ship with a Default / Hover / Pressed / Disabled spec wired through `InteractionSource`. The skill prescribes this pattern; the plan currently doesn't.

> Component-layer tokens (e.g., `ctaCardBackground`, `timelineMarkerCurrent`, `techBadgeBackground`) are deferred until the components that consume them are built (Phase 4–5), so they live next to their consumers instead of bloating `BlogColors` with unused entries.

---

## 3. Implementation phases

### Phase 1 — Pull all 6 frame variants from Figma
Get Light/Dark × Mobile/Tablet/Desktop. Mobile/Dark already pulled (frame `61:2`).

Delegate to a sub-agent so the JSX dumps don't pollute main context. The sub-agent extracts:
- Section structure per breakpoint (preserving Figma ordering).
- Padding / gap deltas between Mobile / Tablet / Desktop.
- Font-size deltas between breakpoints.
- Any tokens that differ between Light and Dark (should be zero — themes share token names, only values change).

Output: a single normalized spec table (per section: padding, gap, font sizes, breakpoint deltas).

### Phase 2 — Resolve design system gaps + tighten 3-layer separation

Split into two commits so the cleanup lands cleanly before any new tokens are added.

**Phase 2a — hex-leak cleanup (no new semantic tokens).** Edit `core/ui/.../BlogColors.kt`:
- Add new primitives so every value used by `DarkColorPalette`/`LightColorPalette` references Layer 1:
  - `SlateBlue` ramp: `SlateBlue3 = #9AADCC`, `SlateBlue5 = #7A8BAA`, `SlateBlue6 = #64748B`, `SlateBlue7 = #4A5A7A` (text + nav slate).
  - `IceLavender = #DEE5FF` (primary text on dark, hero gradient stop).
  - Functional bright tier (Tailwind-400 weight): `FunctionalAmberBright = #FBBF24`, `FunctionalRedBright = #F87171`, `FunctionalGreenBright = #34D399`.
  - Functional deep tier (Tailwind-800 weight): `FunctionalAmberDeep = #92400E`, `FunctionalRedDeep = #991B1B`.
  - Tag dark surfaces: `RustDeep = #3A2510`, `EmeraldDeep = #0A3A2A`.
- Replace all inline `Color(0xFF...)` calls in both palettes with the primitives above.
- Net diff: `BlogColors.kt` semantic layer becomes pure references to Layer 1.

**Phase 2b — new semantic tokens.** Add to `BlogColors`:
- `surfaceSection: Color` — Dark = `Deep5`, Light = `Neutral1` (TBC against light frames in Phase 1).
- `surfaceContainerHigh: Color` — Dark = `Deep1`, Light = `Neutral2`. Distinct from `interactiveSecondaryFill` even when values match.

Cyan `#53DDFC`: stick with `Ocean4` (decided). No new primitive added in Phase 2.

No breaking changes — both phases are additive.

### Phase 3 — Compose resources (text content)

Strategy: **hybrid** — section labels stay in `strings.xml`; structured data (experiences, tech list) lives as Kotlin data classes whose string fields use `stringResource(...)`.

#### Static UI labels (added to `core/ui/.../values/strings.xml` and `values-pt-rBR/strings.xml`)

Section eyebrows + headlines:
- `about_eyebrow_biography` — `BIOGRAPHY` / `BIOGRAFIA`
- `about_headline_bio_line_1`, `about_headline_bio_line_2` — `Crafting Digital` / `Craft` (two-line gradient hero) — copy taken **from Figma**, not résumé. See open question (2).
- `about_bio_paragraph` — bio body copy (Figma copy or résumé summary, see open question 2)
- `about_cta_resume` — `Download Résumé` / `Baixar Currículo`
- `about_eyebrow_arsenal` — `TECH ARSENAL` / `ARSENAL TÉCNICO`
- `about_title_arsenal` — `Tech Arsenal` / `Arsenal Técnico`
- `about_eyebrow_journey` — `THE PROFESSIONAL JOURNEY` / `A JORNADA PROFISSIONAL`
- `about_title_journey` — `Experience Timeline` / `Linha do Tempo Profissional`
- `about_cta_headline` — `Let's build something exceptional.` / `Vamos construir algo excepcional.`
- `about_cta_subtitle` — `Currently open to selective consulting projects and speaking engagements. Let's talk.` / equivalent PT
- `about_cta_primary` — `Hire for Project` / `Contratar`
- `about_cta_secondary` — `Send Email` / `Enviar Email`
- `about_footer_copyright` — `© 2026 Weslley Campos · Digital Craftsmanship.` / `© 2026 Weslley Campos · Artesanato Digital.`
- Social link labels: `about_social_github`, `about_social_linkedin`, `about_social_twitter`

A11y descriptions:
- `a11y_avatar`, `a11y_resume_button`, `a11y_tech_badge`, `a11y_timeline_marker`

#### Experience entries (one set of strings per role, two languages)

The Tech Arsenal list is small enough to keep as a Kotlin `List<TechItem>` with the *display label* as a string resource each. Same for experiences:

```kotlin
data class Experience(
    val period: StringResource,
    val role: StringResource,
    val company: StringResource,
    val description: StringResource,
    val markerColor: Color,
)
```

From `resume.md` / `curriculo.md`, six experiences:
1. SmartHome+ | Telus Digital — Apr 2024 — Present
2. Peloton | Thoughtworks — July 2023 — Apr 2024
3. Invest Voyager | Thoughtworks — Sep 2022 – Jun 2023
4. Tectoy | Eldorado Institute — Jan 2022 – Mar 2022
5. HP | Eldorado Institute — Oct 2021 – Jan 2022
6. Positivo | Eldorado Institute — Apr 2021 – Sep 2021
7. Freelancer Android — Nov 2017 – Feb 2021

Figma shows 3 timeline entries (TELUS / WillowTree / Freelance). The implementation will render **all** entries from the résumé but cap to top 3 on Compact and show full list on Medium/Expanded — to be confirmed against tablet/desktop frames in Phase 1.

Strings keys per experience: `about_exp_<slug>_period`, `_role`, `_company`, `_description` × 2 languages.

#### Tech Arsenal list

Figma shows 14 web-stack badges (TypeScript, React, Next.js, Node.js, Rust, Go, Python, Docker, PostgreSQL, Redis, Tailwind, Figma, Git, AWS). Résumé lists Android-leaning stack (Kotlin, Java, KMP, Compose, Retrofit, Hilt, Koin, etc.).

Open question (3) below — pending answer, default is to use the **résumé list** since it reflects actual expertise. Each item maps to a tuple `(label: StringResource, iconAsset: DrawableResource)`. Icon assets to be added under `core/ui/composeResources/drawable/`.

---

### Phase 4 — Reusable section primitives

#### Promote to `core/ui/components/`

Each lands in its **own commit** with an `@Preview` (light + dark) before the next one starts. Order is bottom-up so later components can compose earlier ones in their previews.

1. **`SectionHeader(eyebrow, title, modifier)`** — eyebrow (`labelMedium`, `letterSpacing 3sp`, `colors.brand`) above a display heading (`displaySmall`, `colors.textPrimary`). Used by every section in About + likely Posts later.

2. **`GradientText(text, brush, style, modifier)`** — wraps `Text` with `TextStyle(brush = brush)`. Default brush = `BlogTheme.gradients.hero`. Used by hero headline + logo.

3. **`BlogPrimaryButton(text, onClick, modifier, enabled)`** — state spec:

   | State | Background | Text | Shadow |
   |---|---|---|---|
   | Default | `gradients.brand` | `interactivePrimaryText` | `shadowButtonColor @ shadowButtonAlpha` |
   | Hover | `gradients.brand` (same brush, `1.04` scale) | same | elevated |
   | Pressed | `gradients.brand` (`0.96` scale) | same | none |
   | Disabled | `surfaceContainerHigh` | `textMuted` | none |

   Implementation: `Box` + `Modifier.clickable(interactionSource, indication = null)` + state-driven `Modifier.scale` derived from `interactionSource.collectIsHoveredAsState()` / `collectIsPressedAsState()`.

4. **`BlogSecondaryButton(text, onClick, modifier, enabled)`** — state spec:

   | State | Background | Text | Border |
   |---|---|---|---|
   | Default | `surfaceContainerHigh` | `interactiveSecondaryText` | none |
   | Hover | `surfaceContainerHigh` blended with `brand @ 0.06` | same | `brand @ 0.3` |
   | Pressed | `surfaceContainerHigh` blended with `brand @ 0.12` | same | `brand @ 0.5` |
   | Disabled | `surfaceContainerHigh @ 0.5` | `textMuted` | none |

#### Local to `feature/about/components/`
- `AboutHero(modifier)` — avatar (120dp circle, gradient brand placeholder until photo asset is added) + biography eyebrow + headline + body + résumé CTA.
- `AboutTechArsenal(items: List<TechItem>, modifier)` — `FlowRow` of 44dp circle badges with 9px label below.
- `AboutTimeline(experiences: List<Experience>, modifier)` — vertical timeline with year-colored markers (gradient line connector).
- `AboutCtaCard(modifier)` — gradient brand background, headline, subtitle, two buttons inline.
- `AboutFooter(modifier)` — social links row + copyright.

---

### Phase 5 — Adaptive layout

Use `LocalScreenSize` (already in place). Three top-level composables in `feature/about/`:
- `AboutCompact` — Compact (≤600 dp), single column, `spacing.xl` horizontal padding.
- `AboutMedium` — Medium (601–840 dp tablet), single column with wider padding (`spacing.xxxl`), larger avatar (160 dp), 2-button-row stays inline.
- `AboutExpanded` — Expanded (≥1200 dp desktop), max-width container (~1200 dp), Bio + CTA on hero band, Tech Arsenal in 7-column grid, Timeline as horizontal row of cards or 2-column grid (verify against Desktop/Dark frame `61:248`).

The screen entry point `AboutScreen()` picks one based on `LocalScreenSize.current` — same pattern as `BlogTopBar`.

---

### Phase 6 — Wire up the screen

- Replace placeholder body of `feature/about/AboutScreen.kt` with the adaptive switch.
- No `AboutViewModel` for now — content is static. Defer until something dynamic appears (resume download tracking, post fetch, etc.).
- `AboutEntryBuilder.kt` already calls `AboutScreen()` — no change needed.

---

### Phase 7 — Previews + verification

- `@Preview` for each section component, light + dark via `BlogTheme(isDarkMode = ...)`.
- `@Preview` for the full screen at the three breakpoints, both themes (6 previews).
- Run `./gradlew :composeApp:wasmJsBrowserDevelopmentRun` and visually verify against the Figma frames.
- Run `./gradlew detekt` (must pass).
- Run `./gradlew jvmTest` (must pass).

---

## 4. Open questions / considerations

These need answers before implementation starts:

1. **Cyan `#53DDFC`** — Keep using `Ocean4` (`#22D3EE`, ~2% off) or introduce a new primitive matching Figma exactly?
   - Default: **use `Ocean4`**, no new primitive.

2. **Hero copy** — Figma shows "Crafting Digital Craft" + "I build systems that are fast and elegant by design. Software developer obsessed with technical performance and minimal aesthetics." Résumé says "Android engineer with 8+ years of experience…". Use Figma copy as-is, or replace with résumé Profile paragraph?
   - Default: **use Figma copy** (matches the design's editorial tone).

3. **Tech Arsenal items** — Figma lists web-stack tools (TypeScript, React, Next.js, Tailwind, Rust, Go…) which don't match the résumé (Kotlin, KMP, Android, Java, Compose…). Use Figma list verbatim or résumé list?
   - Default: **use the résumé list** (reflects actual expertise). This will require sourcing icon assets matching the actual stack.

4. **Strings strategy** — Hybrid (section labels in `strings.xml`, structured data as Kotlin data classes with `StringResource` references) — confirm OK?
   - Default: **hybrid**.

5. **Reusable components placement** — `SectionHeader`, `BlogPrimaryButton`, `BlogSecondaryButton`, `GradientText` proposed for `core/ui/components/`. OK to put them there, or keep everything local to `feature/about/` for now and promote later if reused?
   - Default: **promote to `core/ui`** — they will be reused on Posts and Home.

6. **Timeline depth on Compact** — résumé has 6+ entries; Figma Mobile shows 3. Cap to 3 on Compact and show all on Medium/Expanded, or always show all?
   - Default: **all entries on all breakpoints**, scroll naturally.

7. **Avatar asset** — design shows a 120 dp gradient circle (no actual photo). Use the gradient placeholder from the design, or do you have a portrait asset to drop in?
   - Default: **gradient placeholder** until an asset is provided.

8. **Footer social links** — Figma shows GitHub / LinkedIn / Twitter/X as text links. Résumé has Email / LinkedIn / GitHub. Use the Figma set or the résumé set? Twitter/X is not in the résumé — include it?
   - Default: **GitHub / LinkedIn** from résumé URLs, add **Email** as third link instead of Twitter/X.

9. **Résumé download** — `Download Résumé` button. Behavior?
   - Default: **link to a hosted PDF** at a URL TBD (placeholder action: opens in new tab on web, no-op on JVM until a path exists).

10. **CTA primary button (`Hire for Project`)** — what action? Email link? Calendly? Form?
    - Default: **mailto:weslley.campos@icloud.com** (same as Send Email) until a hiring page exists.

---

## 5. Execution order (branch: `feat/about`)

1. **Phase 2a** — hex-leak cleanup → 1 commit.
2. **Phase 2b** — `surfaceSection` + `surfaceContainerHigh` semantic tokens → 1 commit.
3. **Phase 4 — `core/ui` components**, one commit each (with `@Preview` light + dark):
   - `SectionHeader`
   - `GradientText`
   - `BlogPrimaryButton`
   - `BlogSecondaryButton`
4. **Phase 1** — fetch remaining 5 frames via sub-agent → spec table (deferred until base components exist; the spec only matters once we start composing).
5. **Phase 3** — `strings.xml` additions in EN + PT-BR; data classes for experiences + tech list → 1 commit.
6. **Phase 4 (feature-local)** — `AboutHero`, `AboutTechArsenal`, `AboutTimeline`, `AboutCtaCard`, `AboutFooter` (component-layer color tokens added alongside their consumers) → 1 commit each.
7. **Phase 5** — adaptive `AboutCompact`/`Medium`/`Expanded` composables → 1 commit.
8. **Phase 6** — wire `AboutScreen` (replace placeholder) → 1 commit.
9. **Phase 7** — full-screen previews × 6 + dev-server verification + detekt + jvmTest → 1 commit.

Estimated commits: ~13 on `feat/about`. All component commits include their `@Preview` so design-system additions are visually verifiable per commit, not at the end.
