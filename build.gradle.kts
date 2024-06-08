import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.0" apply false // Spring Bootを使用するために必要
    id("io.spring.dependency-management") version "1.1.5" apply false // Spring Bootの依存関係を一元管理するために必要
    kotlin("jvm") version "1.9.24" apply false // Kotlinを使用するために必要
    kotlin("plugin.spring") version "1.9.24" apply false // Spring Bootを使用するために必要。Kotlinのクラスはデフォルトでfinal。これはSpringの一部の機能と競合するためその解決のために使用する。
}

allprojects {
    group = "com.mrms"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }
}
