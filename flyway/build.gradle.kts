import nu.studer.gradle.jooq.JooqEdition
import org.jooq.meta.jaxb.Logging

// この記載がないと No database found to handle になる
buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.11.0")
    }
}

plugins {
    id("org.flywaydb.flyway") version "10.11.0" // 10.14.0が最新だったが、 No database found to handleのエラーが出たため、出ない一番最新のバージョンを指定
    id("nu.studer.jooq") version "9.0"
}

dependencies {
    jooqGenerator("org.postgresql:postgresql:42.7.3")
    implementation("org.flywaydb:flyway-core:10.11.0")
    runtimeOnly("org.postgresql:postgresql:42.7.3")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/suppletrack"
    user = "postgres"
    password = "password"
    locations = arrayOf("classpath:db/migration")
    cleanDisabled = false
}

tasks.named("flywayMigrate") {
    dependsOn("classes")
}

jooq {
    version.set("3.19.1")
    edition.set(JooqEdition.OSS)

    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/suppletrack"
                    user = "postgres"
                    password = "password"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "supplement"
                        directory = "src/main/kotlin"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

// jOOQの生成タスクをデフォルトで無効化
tasks.named("generateJooq") {
    // デフォルトで無効
    enabled = false
}
