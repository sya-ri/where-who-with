import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.palantir.gradle.gitversion.VersionDetails
import groovy.lang.Closure
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("org.jmailen.kotlinter") version "3.6.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("com.palantir.git-version") version "0.12.3"
    application
}

apply<W3Plugin>()

group = "dev.s7a.w3.server"

val versionDetails: Closure<VersionDetails> by extra
val details = versionDetails()

repositories {
    mavenCentral()
}

val shadowImplementation: Configuration by configurations.creating
val shadowRuntimeOnly: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)
configurations["runtimeOnly"].extendsFrom(shadowRuntimeOnly)

dependencies {
    shadowImplementation(kotlin("stdlib"))
    shadowImplementation("org.jetbrains.exposed:exposed-core:0.35.1")
    shadowImplementation("org.jetbrains.exposed:exposed-dao:0.35.1")
    shadowImplementation("org.jetbrains.exposed:exposed-jdbc:0.35.1")
    shadowImplementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.35.1")
    shadowRuntimeOnly("org.xerial:sqlite-jdbc:3.36.0.3")
    shadowImplementation("ch.qos.logback:logback-classic:1.2.6")
    shadowImplementation("org.jline:jline-reader:3.20.0")
    shadowImplementation("org.jline:jline-builtins:3.20.0")
    shadowImplementation("io.ktor:ktor-server-cio:1.6.3")
    shadowImplementation("io.ktor:ktor-serialization:1.6.3")
    shadowImplementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-test-host:1.6.3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
}

application {
    mainClass.set("$group.MainKt")
}

tasks.withType<GenerateVersionFileTask> {
    val lastTag = details.lastTag
    val commitDistance = details.commitDistance
    val isCleanTag = details.isCleanTag
    version.set("$lastTag${if (commitDistance == 0) "" else "+$commitDistance"}${if (isCleanTag) "" else ".dirty"}")
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation, shadowRuntimeOnly)
    archiveClassifier.set("")
}
