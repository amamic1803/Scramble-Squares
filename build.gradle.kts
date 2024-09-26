repositories {
    mavenCentral()
}

plugins {
    java
    application
}

application {
    mainClass = "com.amamic1803.Main"
    version = "0.1.0"
}

dependencies {
    implementation(group = "io.github.qurben", name = "jico", version = "2.2.0")
    testImplementation(group = "junit", name = "junit", version = "4.13.2")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}
