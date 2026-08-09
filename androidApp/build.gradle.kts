plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.androidx.activity.compose)

    implementation(libs.compose.uiToolingPreview)
    debugImplementation(libs.compose.uiTooling)
}

android {
    namespace = libs.versions.app.id.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.app.id.get()
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.app.versionCode.get().toInt()
        versionName = libs.versions.app.version.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        create("release") {
            storeFile = file(providers.environmentVariable("SIGNING_KEYSTORE_PATH").orNull ?: ".")
            storePassword = providers.environmentVariable("SIGNING_KEYSTORE_PASSWORD").orNull ?: ""
            keyAlias = providers.environmentVariable("SIGNING_KEY_ALIAS").orNull ?: ""
            keyPassword = providers.environmentVariable("SIGNING_KEY_PASSWORD").orNull ?: ""
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jdk.version.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jdk.version.get())
    }
    buildFeatures {
        compose = true
    }
}
