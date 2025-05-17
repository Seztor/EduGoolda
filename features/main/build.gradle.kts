plugins {
    alias(libs.plugins.convetion.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
}

android {
    namespace = "ru.itmo.edugoolda.features.main"
}

dependencies {
    implementation(project(":data:group"))
    implementation(project(":data:solutions"))
    implementation(project(":data:user"))
    ksp(libs.ktorfit.ksp)

    // Modules
    implementation(project(":core"))
    implementation(project(":features:home"))
    implementation(project(":features:group"))
    implementation(project(":features:lesson"))
    implementation(project(":features:profile"))

    // Kotlin
    implementation(libs.kotlinx.datetime)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // UI
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.bundles.coil)

    // DI
    implementation(libs.koin)

    // Logging
    implementation(libs.logger.kermit)

    // Network
    implementation(libs.bundles.ktor)
    implementation(libs.ktorfit.lib)

    implementation(libs.form.validation)

    // Architecture
    implementation(libs.bundles.decompose)
    implementation(libs.bundles.replica)
    api(libs.moko.resources)
    implementation(libs.moko.resourcesCompose)
}
