import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension

plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.HILT)
    id(GradlePluginId.ALL_OPEN) version GradlePluginVersion.KOTLIN_VERSION
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    dataBinding {
        isEnabled = true
    }
    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER

        testInstrumentationRunnerArguments["clearPackageData"] = "true"

        buildConfigField("String", "SPOONACULAR_KEY", findProperty("spoonacularKey") as String)
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    sourceSets {
        getByName("test") {
            java.srcDir("src/test-shared/java")
        }
        getByName("androidTest") {
            java.srcDir("src/test-shared/java")
        }
    }

    lintOptions {
        // By default lint does not check test sources, but setting this option means that lint will not even parse them
        isIgnoreTestSources = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    hilt {
        enableTransformForLocalTests = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
        //execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
}
configure<AllOpenExtension> {
    annotation("com.app.juawcevada.whatscookin.testing.OpenClass")
}

androidExtensions { isExperimental = true }

dependencies {

    implementation(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    implementation(LibraryDependency.NAVIGATION_UI_KTX)
    implementation(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(LibraryDependency.LIFECYCLE_RUNTIME_KTX)
    implementation(LibraryDependency.LOGGING_INTERCEPTOR)
    implementation(LibraryDependency.PLAY_CORE)
    implementation(LibraryDependency.RETROFIT)
    implementation(LibraryDependency.RETROFIT_MOSHI_CONVERTER)
    implementation(LibraryDependency.SPIN_KIT)
    implementation(LibraryDependency.SWIPE_REFRESH_LAYOUT)
    implementation(LibraryDependency.AUTO_COMPLETE)
    implementation(LibraryDependency.HILT_ANDROID)
    kapt(LibraryDependency.SWIPE_REFRESH_LAYOUT)
    kapt(LibraryDependency.HILT_ANDROID_COMPILER)

    implementation(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.COORDINATOR_LAYOUT)
    implementation(LibraryDependency.RECYCLER_VIEW)
    implementation(LibraryDependency.MATERIAL)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.FRAGMENT)
    implementation(LibraryDependency.FRAGMENT_KTX)
    implementation(LibraryDependency.COIL)
    implementation(LibraryDependency.MOSHI)
    kapt(LibraryDependency.MOSHI_CODE_GEM)
    implementation(LibraryDependency.ARROW)

    testImplementation(TestLibraryDependency.JUNIT)
    testImplementation(TestLibraryDependency.MOCKITO)
    testImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
    testImplementation(TestLibraryDependency.COROUTINES_TEST)
    testImplementation(TestLibraryDependency.TEST_CORE)
    testImplementation(TestLibraryDependency.TEST_RULES)
    testImplementation(TestLibraryDependency.TEST_ARCH)

    androidTestImplementation(TestLibraryDependency.ANDROID_TEST_JUNIT)
    androidTestImplementation(TestLibraryDependency.TEST_RUNNER)
    androidTestImplementation(TestLibraryDependency.TEST_RULES)
    androidTestImplementation(TestLibraryDependency.ANDROID_X_TEST_KTS)
    androidTestImplementation(TestLibraryDependency.ESPRESSO_CORE)
    androidTestImplementation(TestLibraryDependency.MOCKITO_ANDROID)
    androidTestImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
    androidTestImplementation(TestLibraryDependency.HILT_TESTING)
    androidTestImplementation(TestLibraryDependency.TEST_CORE)
    androidTestImplementation(TestLibraryDependency.TEST_ARCH)
    androidTestImplementation(TestLibraryDependency.JUNIT_4_13)

    kaptAndroidTest(TestLibraryDependency.HILT_TESTING_COMPILER)

    androidTestUtil(TestLibraryDependency.TEST_ORCHESTRATOR)

    debugImplementation(TestLibraryDependency.FRAGMENT_TESTING)
}
