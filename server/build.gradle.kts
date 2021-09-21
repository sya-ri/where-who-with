import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jmailen.kotlinter") version "3.5.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.github.ben-manes.versions") version "0.39.0"
    application
}

group = "dev.s7a.w3.server"

repositories {
    mavenCentral()
}

val shadowImplementation: Configuration by configurations.creating
val shadowRuntimeOnly: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)
configurations["runtimeOnly"].extendsFrom(shadowRuntimeOnly)

dependencies {
    shadowImplementation(kotlin("stdlib"))
    shadowImplementation("org.jetbrains.exposed:exposed-core:0.34.2")
    shadowImplementation("org.jetbrains.exposed:exposed-dao:0.34.2")
    shadowImplementation("org.jetbrains.exposed:exposed-jdbc:0.34.2")
    shadowImplementation("org.jetbrains.exposed:exposed-java-time:0.34.2")
    shadowRuntimeOnly("org.xerial:sqlite-jdbc:3.36.0.2")
}

application {
    mainClass.set("$group.MainKt")
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation, shadowRuntimeOnly)
    archiveClassifier.set("")
}
