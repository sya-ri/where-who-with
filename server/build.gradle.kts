import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.palantir.gradle.gitversion.VersionDetails
import groovy.lang.Closure
import org.hidetake.gradle.swagger.generator.GenerateSwaggerUI
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    id("org.jmailen.kotlinter") version "3.6.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("com.palantir.git-version") version "0.12.3"
    id("org.hidetake.swagger.generator") version "2.18.2"
    application
}

/*
 * buildSrc/src/main/kotlin にあるプラグインを反映させる
 *
 * タスク
 * - generateVersionFile: version.txt の生成
 */
apply<W3Plugin>()

group = "dev.s7a.w3.server"

/*
 * version.txt の生成に使う
 */
val versionDetails: Closure<VersionDetails> by extra
val details = versionDetails()

repositories {
    mavenCentral()
}

/*
 * 依存関係の同梱に使う
 */
val shadowImplementation: Configuration by configurations.creating
val shadowRuntimeOnly: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)
configurations["runtimeOnly"].extendsFrom(shadowRuntimeOnly)

dependencies {
    // Kotlin
    shadowImplementation(kotlin("stdlib"))

    // データベース
    shadowImplementation("org.jetbrains.exposed:exposed-core:0.35.1")
    shadowImplementation("org.jetbrains.exposed:exposed-dao:0.35.1")
    shadowImplementation("org.jetbrains.exposed:exposed-jdbc:0.35.1")
    shadowImplementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.35.1")
    shadowRuntimeOnly("org.xerial:sqlite-jdbc:3.36.0.3")

    // ロガー
    shadowImplementation("ch.qos.logback:logback-classic:1.2.6")

    // CLI
    shadowImplementation("org.jline:jline-reader:3.20.0")
    shadowImplementation("org.jline:jline-builtins:3.20.0")

    // サーバー
    shadowImplementation("io.ktor:ktor-server-cio:1.6.3")
    shadowImplementation("io.ktor:ktor-serialization:1.6.3")
    shadowImplementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")

    // テスト
    testImplementation(kotlin("test"))

    // サーバーテスト
    testImplementation("io.ktor:ktor-server-test-host:1.6.3")

    // API ドキュメントの生成
    swaggerUI("org.webjars:swagger-ui:3.52.1")
}

/*
 * Java 互換性の設定
 */
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

/*
 * @OptIn アノテーションを使えるようにする
 */
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
}

/*
 * メインクラスの指定
 */
application {
    mainClass.set("$group.MainKt")
}

/*
 * version.txt の生成
 */
tasks.withType<GenerateVersionFileTask> {
    val lastTag = details.lastTag
    val commitDistance = details.commitDistance
    val isCleanTag = details.isCleanTag
    version.set("$lastTag${if (commitDistance == 0) "" else "+$commitDistance"}${if (isCleanTag) "" else ".dirty"}")
}

/*
 * API ドキュメントページの生成
 */
val generateSwaggerUITask = tasks.named<GenerateSwaggerUI>("generateSwaggerUI") {
    inputFile = file("openapi.yaml")
}

/*
 * タスクの実行結果を Jar に同梱する
 */
extensions.getByType<SourceSetContainer>().named(SourceSet.MAIN_SOURCE_SET_NAME) {
    resources.srcDir(generateSwaggerUITask) // API ドキュメント
}

/*
 * shadowImplementation, shadowRuntimeOnly で導入された依存関係を Jar に同梱する
 */
tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation, shadowRuntimeOnly)
    archiveClassifier.set("")
}

/*
 * ビルド時に API ドキュメントの生成を行う
 */
tasks.named("build") {
    dependsOn("generateSwaggerUI")
}
