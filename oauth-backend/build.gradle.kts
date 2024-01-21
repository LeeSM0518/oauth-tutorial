import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    id("com.google.osdetector") version "1.7.0"
}
val springCloudVersion by extra("2023.0.0")

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    if (osdetector.arch.equals("aarch_64")) {
        implementation("io.netty:netty-resolver-dns-native-macos:4.1.89.Final:osx-aarch_64")
    }

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.4")

    // validation
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

    // jwt
    val jwtVersion = "0.11.5"
    implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")

    // test container
    val testContainerVersion = "1.17.3"
    testImplementation("org.testcontainers:testcontainers:$testContainerVersion")
    testImplementation("org.testcontainers:r2dbc:$testContainerVersion")
    testImplementation("org.testcontainers:postgresql:$testContainerVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainerVersion")

    // r2dbc
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:3.0.4")
    implementation("org.postgresql:r2dbc-postgresql:1.0.1.RELEASE")
    runtimeOnly("org.postgresql:postgresql")

    // feign client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
}

// feign client
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
