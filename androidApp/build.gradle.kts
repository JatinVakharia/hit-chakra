plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.hit"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.hit.chakra"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 6
        versionName = "1.5"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
    kotlin {
        jvmToolchain(11)
    }
    signingConfigs {
        create("release") {
            keyAlias = "hitchakra"
            keyPassword = "hitchakra@123"
            storeFile = file("/Users/jatinvakharia/iWork/Personal/EncryptedKeyHitChakra/HitChakraKeyStore.jks")
            storePassword = "hitchakra@123"
        }
    }
}
