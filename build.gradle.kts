import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths
import java.security.MessageDigest
import java.util.zip.ZipInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.apache.commons:commons-compress:1.27.1")
    }
}

val appName = "Scramble-Squares"
val appMainClass = "com.amamic1803.Main"
val appVersion = "0.1.0"
val appIconPath = "${projectDir}/src/main/resources/favicon.ico"

repositories {
    mavenCentral()
}

plugins {
    java
    application
    id("com.gradleup.shadow").version("8.3.2")
    id("edu.sc.seis.launch4j").version("3.0.6")
}

dependencies {
    implementation(group = "io.github.qurben", name = "jico", version = "2.2.0")
    testImplementation(group = "junit", name = "junit", version = "4.13.2")
}

application {
    mainClass = appMainClass
    version = appVersion
}

tasks.withType<edu.sc.seis.launch4j.tasks.DefaultLaunch4jTask> {
    dependsOn(tasks.shadowJar)
    outfile.set("$appName-v$appVersion.exe")
    mainClassName.set(appMainClass)
    icon.set(appIconPath)
    productName.set(appName)
    internalName.set(appName)
    fileDescription.set(appName)
    headerType.set("gui")
    version.set(appVersion)
    textVersion.set(appVersion)
    setJarTask(tasks.shadowJar.get())
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = appMainClass
    }
}

tasks.build {
    dependsOn(tasks.createAllExecutables)
}

val jdks = mapOf(
    "win-x86_64" to "https://download.java.net/java/GA/jdk22.0.2/c9ecb94cd31b495da20a27d4581645e8/9/GPL/openjdk-22.0.2_windows-x64_bin.zip",
    "linux-x86_64" to "https://download.java.net/java/GA/jdk22.0.2/c9ecb94cd31b495da20a27d4581645e8/9/GPL/openjdk-22.0.2_linux-x64_bin.tar.gz",
    "macos-x86_64" to "https://download.java.net/java/GA/jdk22.0.2/c9ecb94cd31b495da20a27d4581645e8/9/GPL/openjdk-22.0.2_macos-x64_bin.tar.gz"
)
val jdkHashes = mapOf(
    "win-x86_64" to "f2a9b9ab944e71a64637fcdc6b13a1188cf02d4eb9ecf71dc927e98b3e45f5dc",
    "linux-x86_64" to "41536f115668308ecf4eba92aaf6acaeb0936225828b741efd83b6173ba82963",
    "macos-x86_64" to "e8b3ec7a7077711223d31156e771f11723cd7af31c2017f1bd2eda20855940fb"
)
tasks.register("downloadJDKs") {
    doLast {
        jdks.forEach { (platform, url) ->
            val fileName = url.substring(url.lastIndexOf("/") + 1)
            val filePath = Paths.get(layout.buildDirectory.asFile.get().toPath().toString(), "tmp", "openJDKs", fileName)
            Files.createDirectories(filePath.parent)

            // Skip download if the file already exists and hashes match
            if (!(Files.exists(filePath) && MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(filePath)).joinToString("") { "%02x".format(it) } == jdkHashes[platform])) {
                // Delete the file if it exists (because the hash doesn't match)
                if (Files.exists(filePath)) {
                    filePath.toFile().delete()
                }

                // Download the file
                URI(url).toURL().openStream().use { input ->
                    Files.copy(input, filePath)
                }

                // Verify the hash
                val expectedHash = jdkHashes[platform]
                val actualHash = MessageDigest.getInstance("SHA-256").digest(Files.readAllBytes(filePath)).joinToString("") { "%02x".format(it) }

                if (actualHash != expectedHash) {
                    filePath.toFile().delete()
                    throw GradleException("Hash verification failed for $fileName")
                }
            }

            // Delete the directory if it exists
            Paths.get(layout.buildDirectory.asFile.get().toPath().toString(), "tmp", "openJDKs", platform).toFile().deleteRecursively()

            // Extract the file
            if (fileName.endsWith(".zip")) {
                ZipInputStream(Files.newInputStream(filePath)).use { zip ->
                    var entry = zip.nextEntry
                    while (entry != null) {
                        val outPath = Paths.get(layout.buildDirectory.asFile.get().toPath().toString(), "tmp", "openJDKs", platform).resolve(entry.name)
                        Files.createDirectories(outPath.parent)
                        if (entry.isDirectory) {
                            Files.createDirectories(outPath)
                        } else {
                            Files.copy(zip, outPath)
                        }
                        zip.closeEntry()
                        entry = zip.nextEntry
                    }
                }
            } else if (fileName.endsWith(".tar.gz")) {
                TarArchiveInputStream(GzipCompressorInputStream(Files.newInputStream(filePath))).use { tar ->
                    var entry = tar.nextEntry
                    while (entry != null) {
                        val outPath = Paths.get(layout.buildDirectory.asFile.get().toPath().toString(), "tmp", "openJDKs", platform).resolve(entry.name)
                        Files.createDirectories(outPath.parent)
                        if (entry.isDirectory) {
                            Files.createDirectories(outPath)
                        } else {
                            Files.copy(tar, outPath)
                        }
                        entry = tar.nextEntry
                    }
                }
            }
        }
    }
}
