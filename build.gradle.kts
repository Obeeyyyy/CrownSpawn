plugins {
    java
}

group = "de.obey.crown"
version = "2.0.5"
val targetJavaVersion = 17

val pluginYml = file("src/main/resources/plugin.yml")
val pluginVersion: String by lazy {
    val versionLine = pluginYml.readLines().find { it.trim().startsWith("version:") }
    versionLine?.split("version:")?.getOrNull(1)?.trim()
        ?: error("Could not find version in plugin.yml")
}

val pluginName: String by lazy {
    val versionLine = pluginYml.readLines().find { it.trim().startsWith("name:") }
    versionLine?.split("name:")?.getOrNull(1)?.trim()
        ?: error("Could not find name in plugin.yml")
}

version = pluginVersion

repositories {
    mavenCentral()
    mavenLocal()
    maven ("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("de.obey.crown.core:CrownCore:1.0.0")
    compileOnly("org.projectlombok:lombok:1.18.32")

    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(targetJavaVersion)
}

tasks.withType<Jar>().configureEach {
    archiveBaseName.set(pluginName)
    archiveVersion.set(pluginVersion)
}
