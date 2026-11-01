plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.18.2"
}

apiValidation {
    ignoredProjects.add("sample")
}

allprojects {
    group = "io.github.tauseef-ahmad-dev"
    version = "0.1.0"
}
