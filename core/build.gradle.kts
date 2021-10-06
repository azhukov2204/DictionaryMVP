plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.JAVA_VERSION
        targetCompatibility = Config.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }
}

dependencies {
    implementation(project(Modules.MODEL))
    implementation(Design.CORE)
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    testImplementation(TestImpl.JUNIT)
    androidTestImplementation(TestImpl.ANDROIDX_JUNIT)
    androidTestImplementation(TestImpl.ESPRESSO)
    implementation(Kotlin.KOTLINX_COROUTINES_CORE)
    implementation(Kotlin.KOTLINX_COROUTINES_ANDROID)
}