import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var javaVersion = JavaVersion.VERSION_12
val kotlinVersion = "1.3.72"

plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "io.github.marmer.kata"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))
    testImplementation(kotlin("test", kotlinVersion))
    testImplementation(kotlin("test-junit5", kotlinVersion))
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.2")
}

configure<JavaPluginConvention>{
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}



tasks.withType<Test>() {
    useJUnitPlatform()
    testLogging {
        events(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.STANDARD_OUT,
            TestLogEvent.STANDARD_ERROR
        )

        exceptionFormat = TestExceptionFormat.FULL
        showExceptions  =true
        showCauses = true
        showStackTraces = true
    }
}


tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.javaParameters = true
    kotlinOptions.jvmTarget = javaVersion.toString()
}
