package com.example.zeytunpharma.buildSrc

object Versions {
const val ktlint = "0.41.0"
}
object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.1.5"

    object Accompanist {
        const val version = "0.18.0"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
    }

    object Kotlin {
        private const val version = "1.5.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val palette = "androidx.palette:palette:1.0.0"

        const val coreKtx = "androidx.core:core-ktx:1.6.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.1"
        }

        object Constraint {
            const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
        }

        object Compose {
            const val snapshot = ""
            const val version = "1.0.2"

            @get:JvmStatic
            val snapshotUrl: String
                get() = "https://androidx.dev/snapshots/builds/$snapshot/artifacts/repository/"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val foundation = "androidx.compose.foundation:foundation:${version}"
            const val layout = "androidx.compose.foundation:foundation-layout:${version}"

            const val ui = "androidx.compose.ui:ui:${version}"
            const val material = "androidx.compose.material:material:${version}"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${version}"

            const val tooling = "androidx.compose.ui:ui-tooling:${version}"
        }

        object Lifecycle {
            private const val version = "2.3.1"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }
        object Navigation {
            const val navigationCompose = "androidx.navigation:navigation-compose:2.4.0-alpha06"
        }
        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }

        object Room {
            private const val version = "2.3.0"
            const val runtime = "androidx.room:room-runtime:${version}"
            const val ktx = "androidx.room:room-ktx:${version}"
            const val compiler = "androidx.room:room-compiler:${version}"
        }
    }

    object Rome {
        private const val version = "1.14.1"
        const val rome = "com.rometools:rome:$version"
        const val modules = "com.rometools:rome-modules:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.3.0"
    }
    }