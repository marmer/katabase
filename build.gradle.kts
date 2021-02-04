import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var javaVersion = JavaVersion.VERSION_11
val kotlinVersion = "1.4.0"

plugins {
    java
    kotlin("jvm") version "1.4.0"
    id("org.sonarqube") version "3.0"
    jacoco
    id("io.freefair.lombok") version "5.0.1"
    id("com.github.node-gradle.node") version "2.2.4"
    application
}

//Can be run wich gradle run --args="..."
application {
    mainClass.set("io.github.marmer.ApplicationKt")
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
    testImplementation("org.assertj", "assertj-core", "3.16.1")
    testImplementation("org.hamcrest", "hamcrest-core", "2.2")
    testImplementation("org.mockito", "mockito-core", "3.3.3")
    testImplementation("org.mockito", "mockito-junit-jupiter", "3.3.3")
    testImplementation("com.nhaarman.mockitokotlin2", "mockito-kotlin", "2.2.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}

sonarqube {
    properties {
        // must be unique in a given SonarQube instance
        property("sonar.projectKey", "io.github.marmer.katas:katabase")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "marmer-github")
        // --- optional properties ---
        // defaults to project key
        property("sonar.projectName", "katabase")
        // defaults to 'not provided'
        //property("sonar.projectVersion","1.0")
        // Path is relative to the sonar-project.properties file. Defaults to .
        property("sonar.sources", sourcePathDirectoriesIn("src/main"))
        property("sonar.tests", sourcePathDirectoriesIn("src/test"))
        property("sonar.sii.coverage.ut.js.report.path", "coverage/lcov.info")
        property("sonar.sii.coverage.ut.ts.report.path", "coverage/lcov.info")
        property("sonar.test.inclusions", "**/*Test*/**")
        property("sonar.exclusions", "**/*Test*/**")
        // Encoding of the source code. Default is default system encoding
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.kotlin.coveragePlugin", "jacoco")
        property("sonar.log.level", "DEBUG")
        property("sonar.verbose", "true")
    }
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
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}


tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.javaParameters = true
    kotlinOptions.jvmTarget = javaVersion.toString()
}

fun sourcePathDirectoriesIn(sourtePathRoot: String): String {
    return (File(rootDir, sourtePathRoot)
        .listFiles(File::isDirectory) ?: emptyArray())
        .map { it.name }
        .joinToString(",") { sourtePathRoot + "/" + it }
}
