import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
//    androidTarget()
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    jvmToolchain(17)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Ktor
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(compose.material3)
            // Navigation
            implementation(libs.navigation.compose)

            // ViewModel
            implementation(libs.androidx.lifecycle.viewmodel)

            //dataStore
            implementation(libs.androidx.data.store.core)

            //Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.compose.core)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor3)
            //kamel
//            implementation(libs.kamel.image)

            // Ktor
            implementation(libs.ktor.core)
            implementation(libs.ktor.json)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.negotiation)
            implementation(libs.kotlinx.serialization.json)


            //Kermit  for logging
            implementation(libs.kermit)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            // ktor
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.swing)
        }
        iosMain.dependencies {

            // Ktor
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "org.himanshu.kmp_news"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.himanshu.kmp_news"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.androidx.animation.android)
    implementation(libs.androidx.ui.graphics.android)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.himanshu.kmp_news.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.himanshu.kmp_news"
            packageVersion = "1.0.0"
        }
    }
}

buildkonfig {
    packageName = "org.himanshu.kmp_news"

    val localProperties =
        Properties().apply {
            val propsFile = rootProject.file("local.properties")
            if (propsFile.exists()) {
                load(propsFile.inputStream())
            }
        }

    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING,
            "API_KEY",
            localProperties["API_KEY"]?.toString() ?: "",
        )
    }
}
