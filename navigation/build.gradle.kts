plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget()

    iosX64 {
        binaries.framework {
            baseName = "navigationKit"
        }
    }
    iosArm64 {
        binaries.framework {
            baseName = "navigationKit"
        }
    }
    iosSimulatorArm64 {
        binaries.framework {
            baseName = "navigationKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.common)
                implementation(libs.kotlin.stdlib)
                implementation(libs.compose.navigation)
                api(projects.feature.root)
                api(projects.feature.onBoarding)
                api(projects.feature.chat)
            }
        }
        // Define androidMain if needed
    }
}

android {
    namespace = "com.example.navigation"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}
