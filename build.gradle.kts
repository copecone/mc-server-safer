plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.dokka") version "1.6.10" apply false
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}


subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.18-R0.1-SNAPSHOT")

        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

        implementation("io.github.monun:heartbeat-coroutines:0.0.3")
    }
}

listOf("api", "core").forEach { projectName ->
    project(":${rootProject.name}-$projectName") {
        apply(plugin = "org.jetbrains.dokka")

        tasks {
            create<Jar>("sourcesJar") {
                archiveClassifier.set("sources")
                from(sourceSets["main"].allSource)
            }

            create<Jar>("dokkaJar") {
                archiveClassifier.set("javadoc")
                dependsOn("dokkaHtml")

                from("$buildDir/dokka/html/") {
                    include("**")
                }
            }
        }
    }
}

tasks {
    register<DefaultTask>("setupModules") {
        doLast {
            val defaultPrefix = "sample"
            val projectPrefix = rootProject.name

            if (defaultPrefix != projectPrefix) {
                fun rename(suffix: String) {
                    val from = "$defaultPrefix-$suffix"
                    val to = "$projectPrefix-$suffix"
                    file(from).takeIf { it.exists() }?.renameTo(file(to))
                }

                rename("api")
                rename("core")
                rename("dongle")
                rename("plugin")
                rename("publish")
            }
        }
    }
}
