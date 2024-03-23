import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.util.Properties

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.buildkonfig)
    id("dev.icerock.mobile.multiplatform-resources")
}

multiplatformResources {
    multiplatformResourcesPackage = "com.diphrogram.qstestapp" //  required
    disableStaticFrameworkWarning = true
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.voyager.navigator)
                implementation(libs.voyager.navigator.tab)
                implementation(libs.voyager.navigator.vm)
                implementation(libs.voyager.navigator.koin)

                implementation(libs.composeImageLoader)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.kotlin.serialization)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
                implementation(libs.multiplatformSettings)
                implementation(libs.multiplatformSettings.noarg)
                implementation(libs.koin.core)

                // Moko res
                implementation(libs.moko.resource)
                implementation(libs.moko.resource.compose)

                // Coil
                implementation(libs.coil)
                implementation(libs.coil.network)

                // Touchlab
                implementation(libs.touchlab)
            }
        }


        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
                implementation(libs.kotlinx.coroutines.android)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.sqlDelight.driver.android)
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.sqlDelight.driver.native)
            }
        }
    }
}

android {
    namespace = "com.diphrogram.qstestapp"
    compileSdk = 34

    defaultConfig {
        minSdk = 22
        targetSdk = 34

        applicationId = "com.diphrogram.qstestapp.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}
dependencies {
	implementation(libs.androidx.core)
}

buildkonfig {
    packageName = "com.diphrogram.qstestapp"
    // objectName = 'YourAwesomeConfig'
    // exposeObjectWithName = 'YourAwesomePublicConfig'

    val properties = Properties()
    val propertiesFile = File("$rootDir/local.properties")
    if (propertiesFile.exists()) {
        properties.load(project.rootProject.file("local.properties").inputStream())
    }
    defaultConfigs {
        buildConfigField (FieldSpec.Type.STRING, "API_KEY_TMDB", "${properties.getProperty("API_KEY_TMDB")}")
    }

    // Get the API keys from local.properties
    // Set API keys in BuildConfig

}

buildConfig {
    // BuildConfig configuration here.
    // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts
}

sqldelight {
    databases {
        create("MyDatabase") {
            // Database configuration here.
            // https://cashapp.github.io/sqldelight
            packageName.set("com.diphrogram.qstestapp.db")
        }
    }
}
