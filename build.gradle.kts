plugins {
    kotlin("jvm") version "1.4.20"
    id("io.quarkus") version "1.10.2.Final"
}

group = "io.bsamartins.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.quarkus:quarkus-bom:1.10.2.Final"))

    implementation(kotlin("stdlib"))
    implementation("io.quarkus:quarkus-kubernetes-client")
    implementation("io.javaoperatorsdk:operator-framework:1.3.0")
}
