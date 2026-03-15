import org.gradle.kotlin.dsl.invoke

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
}


kotlin {

// Target declarations - add or remove as needed below. These define
// which platforms this KMP module supports.
// See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.example.common"
        compileSdk = 35
        minSdk = 24

        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        packaging {
            resources {
                excludes += "/META.INF/{AL2.0,LGPL2.1}"
                pickFirsts += "**/composeResources/**"
            }

        }
    }

// For iOS targets, this is also where you should
// configure native binary output. For more information, see:
// https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

// A step-by-step guide on how to include this library in an XCode
// project can be found here:
// https://developer.android.com/kotlin/multiplatform/migrate
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "navigation"
            isStatic = true
        }
    }

// Source set declarations.
// Declaring a target automatically creates a source set with the same name. By default, the
// Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
// common to share sources between related targets.
// See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.ktor.client.cio)
            api(libs.compose.ui.tooling.preview)
            api(compose.preview)

        }
        commonMain {
            dependencies {
                api(libs.kotlin.stdlib)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.ui)
                api(compose.components.resources)
                api(libs.androidx.lifecycle.runtimeCompose)
                api(libs.koin.core)
                api(libs.kotlinx.serialization)
                api(libs.androidx.lifecycle.viewmodel)
                api(libs.kotlinx.datetime.v062)
                api(libs.kdeviceinfo)
                api(libs.ktor.client.content.negotiation)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.ktor.client.logging)
                api(libs.coil.network.ktor)
                api(libs.coil.compose)
                api(libs.ktor.client.cio)
                api(compose.materialIconsExtended)
                api(libs.napier)
                api(libs.ktor.client.core)
                api(libs.multiplatform.settings.coroutines)
                api(libs.multiplatform.settings.no.arg)
                api(libs.koin.compose)
                api(libs.koin.core)
                api(libs.koin.compose.viewmodel)
                api(libs.compose.ui.tooling.preview)
                api(compose.components.uiToolingPreview)

            }
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.ktor.client.ios)
        }
    }

}
compose.resources {
    publicResClass = true
    generateResClass = always
}
