plugins {
    kotlin("jvm") version "2.0.20"

}

group = "org.spike.markdown"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.commonmark:commonmark:0.24.0")
    implementation("com.vladsch.flexmark:flexmark-all:0.64.8")
    implementation("org.jetbrains:markdown:0.7.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}