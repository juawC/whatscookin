object GradlePluginVersion {
    const val SAFE_ARGS = CommonVersion.NAVIGATION
    const val BUILD_TOOLS_VERSION = "4.1.0-beta03"
    const val KOTLIN_VERSION = "1.3.21"
    const val HILT_VERSION = "2.28-alpha"
}

object GradlePluginId {
    const val ANDROID_GRADLE_PLUGIN =
        "com.android.tools.build:gradle:${GradlePluginVersion.BUILD_TOOLS_VERSION}"
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradlePluginVersion.KOTLIN_VERSION}"
    const val SAFE_ARGS_GRADLE_PLUGIN =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${GradlePluginVersion.SAFE_ARGS}"
    const val ALL_OPEN = "org.jetbrains.kotlin.plugin.allopen"
    const val ANDROID_APPLICATION = "com.android.application"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val HILT_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${GradlePluginVersion.HILT_VERSION}"
    const val HILT = "dagger.hilt.android.plugin"
}
