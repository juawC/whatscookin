@file:Suppress("detekt.StringLiteralDuplication")

private object LibraryVersion {
    const val RETROFIT = "2.6.2"
    const val LOGGING_INTERCEPTOR = "4.2.0"
    const val PLAY_CORE = "1.6.3"
    const val RECYCLER_VIEW = "1.0.0"
    const val COORDINATOR_LAYOUT = "1.0.0"
    const val MATERIAL = "1.2.0-alpha05"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.1.0"
    const val FRAGMENT_KTX = "1.1.0"
    const val FRAGMENT = "1.1.0"
    const val LIFECYCLE_KTX = "2.2.0"
    const val COIL = "0.9.5"
    const val MOSHI = "1.9.2"
    const val ARROW = "0.10.4"
    const val SPIN_KIT = "1.4.0"
    const val AUTO_COMPLETE = "1.1.0"
    const val DAGGER = "2.26"
    const val SWIPE_REFRESH_LAYOUT = "1.0.0"
    const val HILT = "2.28-alpha"
}

object LibraryDependency {
    // Required by Android dynamic feature modules and SafeArgs
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val MOSHI = "com.squareup.moshi:moshi-kotlin:${LibraryVersion.MOSHI}"
    const val ARROW = "io.arrow-kt:arrow-core:${LibraryVersion.ARROW}"
    const val MOSHI_CODE_GEM = "com.squareup.moshi:moshi-kotlin-codegen:${LibraryVersion.MOSHI}"
    const val RETROFIT_MOSHI_CONVERTER =
        "com.squareup.retrofit2:converter-moshi:${LibraryVersion.RETROFIT}"
    const val LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.LOGGING_INTERCEPTOR}"
    const val SUPPORT_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val PLAY_CORE = "com.google.android.play:core:${LibraryVersion.PLAY_CORE}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLER_VIEW}"
    const val COORDINATOR_LAYOUT =
        "androidx.coordinatorlayout:coordinatorlayout:${LibraryVersion.COORDINATOR_LAYOUT}"
    const val MATERIAL = "com.google.android.material:material:${LibraryVersion.MATERIAL}"
    const val CORE_KTX = "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${LibraryVersion.FRAGMENT_KTX}"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:${LibraryVersion.FRAGMENT}"
    const val LIFECYCLE_VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.LIFECYCLE_KTX}"
    const val LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${LibraryVersion.LIFECYCLE_KTX}"
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${CommonVersion.NAVIGATION}"
    const val NAVIGATION_UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${CommonVersion.NAVIGATION}"
    const val COIL = "io.coil-kt:coil:${LibraryVersion.COIL}"
    const val SPIN_KIT = "com.github.ybq:Android-SpinKit:${LibraryVersion.SPIN_KIT}"
    const val AUTO_COMPLETE = "com.otaliastudios:autocomplete:${LibraryVersion.AUTO_COMPLETE}"
    const val DAGGER = "com.google.dagger:dagger:${LibraryVersion.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${LibraryVersion.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${LibraryVersion.DAGGER}"
    const val DAGGER_ANDROID_SUPPORT =
        "com.google.dagger:dagger-android-support:${LibraryVersion.DAGGER}"
    const val DAGGER_ANDROID_COMPILER =
        "com.google.dagger:dagger-android-processor:${LibraryVersion.DAGGER}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${LibraryVersion.SWIPE_REFRESH_LAYOUT}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${LibraryVersion.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${LibraryVersion.HILT}"
}
