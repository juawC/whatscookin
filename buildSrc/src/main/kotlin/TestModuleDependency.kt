import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addTestDependencies() {

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

    kaptAndroidTest(TestLibraryDependency.HILT_TESTING_COMPILER)

    debugImplementation(TestLibraryDependency.FRAGMENT_TESTING)
}

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.kaptAndroidTest(dependencyNotation: Any): Dependency? =
    add("kaptAndroidTest", dependencyNotation)
