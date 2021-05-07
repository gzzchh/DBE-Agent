import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


plugins {
    kotlin("jvm") version "1.3.72"
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
    flatDir {
        dirs("libs")
    }

}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    implementation("net.bytebuddy:byte-buddy-agent:1.11.0")
    implementation("net.bytebuddy:byte-buddy:1.11.0")
    implementation(
            fileTree(
                    // libs 就是文件夹名字
                    mapOf("dir" to "libs", "include" to listOf("*.jar"))
            )
    )
}

tasks {
    withType<Jar> {
        // Otherwise you'll get a "No main manifest attribute" error
        manifest {
//            attributes["Main-Class"] = "dev.misakacloud.dbee.Main"
            attributes["Premain-Class"] = "dev.misakacloud.dbee.Agent"
            attributes["Can-Retransform-Classes"] = "true"
            attributes["Can-Redefine-Classes"] = "true"
            attributes["Boot-Class-Path"] = "dbeaver-agent-all.jar"
        }

        // To add all of the dependencies otherwise a "NoClassDefFoundError" error
        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)

    }
    named<ShadowJar>("shadowJar") {
//        archiveBaseName.set("dbeaver-agent-all.jar")
        archiveFileName.set("dbeaver-agent-all.jar")
        mergeServiceFiles()
    }
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    test {
        // Use junit platform for unit tests.
        useJUnitPlatform()
    }
}




