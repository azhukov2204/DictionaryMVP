import java.util.*

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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.put("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        forEach {
            val properties = Properties()
            properties.load(project.rootProject.file("apikey.properties").inputStream())
            val dictionaryApiKey = properties.getProperty("DICTIONARY_API_KEY", "")
            it.buildConfigField("String", "DICTIONARY_API_KEY", dictionaryApiKey)
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
    testImplementation(TestImpl.JUNIT)
    androidTestImplementation(TestImpl.ANDROIDX_JUNIT)
    androidTestImplementation(TestImpl.ESPRESSO)
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.RETROFIT_GSON)
    implementation(Retrofit.RETROFIT_COROUTINES)
    implementation(Retrofit.LOGGING_INTERCEPTOR)
    implementation(Room.ROOM_RUNTIME)
    implementation(Room.ROOM_KTX)
    kapt(Room.ROOM_COMPILER)
    implementation(Koin.KOIN_CORE)
    implementation(Koin.KOIN_ANDROID)
}