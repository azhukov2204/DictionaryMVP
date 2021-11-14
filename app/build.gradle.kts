plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Releases.VERSION_CODE
        versionName = Releases.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            versionNameSuffix = "-debug"
        }
    }
    compileOptions {
        sourceCompatibility = Config.JAVA_VERSION
        targetCompatibility = Config.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.MODEL))
    implementation(project(Modules.PRESENTATION))
    implementation(Design.CORE)
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINT_LAYOUT)
    implementation(Design.LEGACY)
    testImplementation(TestImpl.JUNIT)
    androidTestImplementation(TestImpl.ANDROIDX_JUNIT)
    androidTestImplementation(TestImpl.ESPRESSO)
    implementation(Koin.KOIN_CORE)
    implementation(Koin.KOIN_ANDROID)
    implementation(Navigation.CICERONE)
    debugImplementation(TestImpl.ESPRESSO_CONTRIB)
}
