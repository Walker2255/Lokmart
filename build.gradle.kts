buildscript {
    val compose_version by extra("1.3.0-alpha01")

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.4.1" apply false
    id("com.android.library") version "7.4.0-alpha08" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
