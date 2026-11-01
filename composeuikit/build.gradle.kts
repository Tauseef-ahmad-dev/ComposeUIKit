plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.vanniktech.maven.publish") version "0.37.0"
}

android {
    namespace = "dev.tauseef.composeuikit"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    // These are part of public composable/theme signatures and must be exposed to consumers.
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.material3)

    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

mavenPublishing {
    coordinates(
        "io.github.tauseef-ahmad-dev",
        "composeuikit",
        project.version.toString()
    )

    // Upload to Central Portal, but leave the final Publish action for a human review.
    publishToMavenCentral()
    // Sign artifacts only when the in-memory signing key is supplied (e.g. the Maven Central workflow).
    // Local Maven publication and regular CI builds should not require release credentials.
    if (providers.gradleProperty("signingInMemoryKey").isPresent) {
        signAllPublications()
    }

    pom {
        name.set("ComposeUIKit")
        description.set("Reusable Jetpack Compose UI components built with Kotlin and Material 3.")
        url.set("https://github.com/Tauseef-ahmad-dev/ComposeUIKit")
        inceptionYear.set("2026")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("Tauseef-ahmad-dev")
                name.set("Tauseef Ahmad")
                url.set("https://github.com/Tauseef-ahmad-dev")
            }
        }

        scm {
            connection.set("scm:git:https://github.com/Tauseef-ahmad-dev/ComposeUIKit.git")
            developerConnection.set("scm:git:ssh://git@github.com/Tauseef-ahmad-dev/ComposeUIKit.git")
            url.set("https://github.com/Tauseef-ahmad-dev/ComposeUIKit")
        }
    }
}

// Keep a local Maven destination for CI and consumer smoke tests.
publishing {
    repositories {
        maven {
            name = "local"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}
