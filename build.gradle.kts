buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(GradlePluginId.ANDROID_GRADLE_PLUGIN)
        classpath(GradlePluginId.KOTLIN_GRADLE_PLUGIN)
        classpath(GradlePluginId.SAFE_ARGS_GRADLE_PLUGIN)
        classpath(GradlePluginId.HILT_PLUGIN)
    }
}

allprojects {
    repositories {
        google()
        jcenter()

    }
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }
}
