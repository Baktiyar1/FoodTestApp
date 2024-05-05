plugins {
    id("java-library")
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    // TEST
    // JUnit
    testImplementation(libs.junit)
    // Mockk
    testImplementation(libs.mockk)
    // Coroutines
    testImplementation(libs.kotlinx.coroutines.test)

}