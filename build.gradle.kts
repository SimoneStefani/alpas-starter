import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application")
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "__PACKAGE__"
version = "1.0.0"

application {
    mainClassName = "__PACKAGE__.StartKt"
    applicationName = "__APP_NAME__"
    executableDir = "./build/install/__APP_NAME__"
}

repositories {
    jcenter()
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
    val kotlin_version = "1.6.10"
    val alpas_version = "83ac507ca1"

    implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))
    implementation("com.github.SimoneStefani.alpas:alpas:$alpas_version")
    implementation("ch.qos.logback:logback-classic:1.3.0-alpha12")
    implementation("mysql:mysql-connector-java:8.0.19")
    implementation(kotlin("reflect", version = kotlin_version))

    testImplementation("com.github.SimoneStefani.alpas:pulsar:$alpas_version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<ShadowJar>("shadowJar") {
    destinationDirectory.file("./")
    archiveBaseName.set("sampleapp")
    //archiveClassifier.set(null)
    //archiveVersion.set(null)

    manifest {
        attributes(mapOf("Main-Class" to "__PACKAGE__.StartKt"))
    }
}
