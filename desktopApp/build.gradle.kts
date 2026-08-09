import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(libs.versions.jdk.version.get())
    }
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jdk.version.get()))
        vendor.set(JvmVendorSpec.matching(libs.versions.jdk.vendor.get()))
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)
}

compose.desktop {
    application {
        mainClass = libs.versions.app.id.get() + ".MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = libs.versions.app.id.get()
            description = libs.versions.app.description.get()
            packageVersion = libs.versions.app.version.get()

            macOS {
                bundleID = libs.versions.app.id.get()
                //iconFile.set(project.file("icons/icon.icns"))
            }
            windows {
                menuGroup = "Games"
                //iconFile.set(project.file("icons/icon.ico"))
            }
            linux {
                menuGroup = "Games"
                //iconFile.set(project.file("icons/icon.png"))
            }
        }
    }
}
