
plugins {
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "space.dragota.panelka.api"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}


sourceSets {
	create("integration-test") {
		compileClasspath += sourceSets["main"].output
		runtimeClasspath += sourceSets["main"].output
	}
}
configurations {
	"integration-testImplementation" {
		extendsFrom(configurations.implementation.get())
	}
	"integration-testRuntimeOnly" {
		extendsFrom(configurations.runtimeOnly.get())
	}
}

repositories {
	mavenCentral()
}
dependencies {
	implementation(project(":core"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	"integration-testImplementation"(project(":core"))
	"integration-testImplementation"("org.testcontainers:junit-jupiter:1.18.3")
	"integration-testImplementation"("org.testcontainers:postgresql:1.18.3")
	"integration-testImplementation"("org.springframework.boot:spring-boot-starter-test")
	"integration-testImplementation"("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.register<Test>("integrationTest") {
	group = "verification"
	testClassesDirs = sourceSets["integration-test"].output.classesDirs
	classpath = sourceSets["integration-test"].runtimeClasspath
	mustRunAfter(tasks["test"])
}