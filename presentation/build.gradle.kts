plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.MODEL))
    implementation(project(Modules.CORE))
    implementation(project(Modules.UTILS))
    implementation(Design.CORE)
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    testImplementation(TestImpl.JUNIT)
    androidTestImplementation(TestImpl.ANDROIDX_JUNIT)
    androidTestImplementation(TestImpl.ESPRESSO)
    testImplementation(TestImpl.MOCKITO_CORE)
    testImplementation(TestImpl.MOCKITO_INLINE)
    testImplementation(TestImpl.MOCKITO_KOTLIN)
    testImplementation(TestImpl.CORE_TESTING)
    testImplementation(TestImpl.KOTLINX_COROUTINES)
    implementation(ViewBinding.VIEW_BINDING_PROPERTY_DELEGATE)
    implementation(Kotlin.KOTLINX_COROUTINES_CORE)
    implementation(Kotlin.KOTLINX_COROUTINES_ANDROID)
    implementation(Kotlin.LIFECYCLE_LIVEDATA_KTX)
    implementation(Kotlin.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(Koin.KOIN_CORE)
    implementation(Koin.KOIN_ANDROID)
    implementation(ImageLoaders.GLIDE)
    kapt(ImageLoaders.GLIDE_COMPILER)
    implementation(Navigation.CICERONE)
    debugImplementation(TestImpl.FRAGMENT_TESTING)
    debugImplementation(TestImpl.ESPRESSO_CONTRIB)
}
