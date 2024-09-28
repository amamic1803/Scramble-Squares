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
    outfile.set("$appName-v$appVersion-win-x86_64-portable.exe")
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

tasks.jar {
    archiveBaseName.set(appName)
    archiveVersion.set("v$appVersion")
    archiveClassifier.set("thin")
    manifest {
        attributes["Main-Class"] = appMainClass
    }
}

tasks.shadowJar {
    archiveBaseName.set(appName)
    archiveVersion.set("v$appVersion")
    archiveClassifier.set("")
}

tasks.build {
    dependsOn(tasks.createAllExecutables)
}
