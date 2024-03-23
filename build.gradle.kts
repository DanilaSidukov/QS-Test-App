buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath (libs.kotlin.gradle.plugin)
        classpath (libs.buildkonfig.gradle.plugin)
        classpath (libs.moko.classpath)
    }
}

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.buildConfig).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
    alias(libs.plugins.buildkonfig).apply(false)
}
