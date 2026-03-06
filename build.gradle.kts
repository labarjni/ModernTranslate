plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
}

group = "ru.labarjni"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
    maven("https://repo.lanink.cn/repository/maven-public/")
    maven("https://repo.densy.org/snapshots")
}

dependencies {
    compileOnly("cn.nukkit:Nukkit:MOT-SNAPSHOT")

    implementation("org.densy.polyglot:api:1.1.3-SNAPSHOT")
    implementation("org.densy.polyglot:common:1.1.3-SNAPSHOT")
    implementation("org.densy.polyglot:core:1.1.3-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveClassifier.set("")
        archiveFileName.set("ModernTranslate-${project.version}.jar")

        isEnableRelocation = false

        manifest {
            attributes(
                "Main-Class" to "ru.labarjni.translations.ModernTranslate",
                "Implementation-Version" to project.version
            )
        }
    }

    build {
        dependsOn(shadowJar)
    }
}