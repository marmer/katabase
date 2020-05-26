import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var javaVersion = JavaVersion.VERSION_12
val kotlinVersion = "1.3.72"

plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("org.sonarqube") version "2.8"
    jacoco
    id("io.freefair.lombok") version "5.0.1"
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
        property("sonar.test.inclusions", "**/*Test*/**")
        property("sonar.exclusions", "**/*Test*/**")
        // Encoding of the source code. Default is default system encoding
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.kotlin.coveragePlugin", "jacoco")
        //property("sonar.log.level=DEBUG)
        //property("sonar.verbose=true)
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

fun sourcePathDirectoriesIn(srcMain: String): String {
    return (File(rootDir, srcMain)
        .listFiles(File::isDirectory) ?: emptyArray())
        .map { it.name }
        .joinToString(",") { srcMain + "/" + it }
}
