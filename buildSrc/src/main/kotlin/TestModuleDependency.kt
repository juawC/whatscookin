import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addTestDependencies() {

    testImplementation(TestLibraryDependency.JUNIT)
    testImplementation(TestLibraryDependency.MOCKITO_INLINE)
    testImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
    testImplementation(TestLibraryDependency.COROUTINES_TEST)
    testImplementation(TestLibraryDependency.ANDROID_X_CORE_TESTING)
    testImplementation(TestLibraryDependency.ANDROID_X_TEST_RULES)
    androidTestImplementation(TestLibraryDependency.ANDROID_X_CORE_TESTING)
    androidTestImplementation(TestLibraryDependency.ANDROID_X_TEST_RULES)
    androidTestImplementation(TestLibraryDependency.TEST_RUNNER)
    androidTestImplementation(TestLibraryDependency.ANDROID_X_TEST_KTS)
    androidTestImplementation(TestLibraryDependency.ESPRESSO_CORE)
    androidTestImplementation(TestLibraryDependency.MOCKITO_ANDROID)
    androidTestImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
    debugImplementation(TestLibraryDependency.FRAGMENT_TESTING)
}

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)
