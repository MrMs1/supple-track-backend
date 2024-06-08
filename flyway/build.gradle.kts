// この記載がないと No database found to handle になる
buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:10.11.0")
    }
}

plugins {
    id("org.flywaydb.flyway") version "10.11.0" // 10.14.0が最新だったが、 No database found to handleのエラーが出たため、出ない一番最新のバージョンを指定
}

dependencies {
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
