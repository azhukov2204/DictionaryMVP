import org.gradle.api.JavaVersion

object Config {
    const val APPLICATION_ID = "ru.androidlearning.dictionary"
    const val COMPILE_SDK = 31
    const val MIN_SDK = 21
    const val TARGET_SDK = 31
    const val JVM_TARGET = "1.8"
    val JAVA_VERSION = JavaVersion.VERSION_1_8
}

object Releases {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
}

object Modules {
    const val MODEL = ":model"
    const val CORE = ":core"
    const val PRESENTATION = ":presentation"
    const val UTILS = ":utils"
}

object Versions {
    const val GRADLE = "7.0.3"
    const val KOTLIN_GRADLE_PLUGIN = "1.5.31"
    const val RETROFIT2 = "2.9.0"
    const val KOIN = "3.1.2"
    const val KOTLINX_COROUTINES = "1.5.2"
    const val LIFECYCLE_KTX = "2.3.1"
    const val RETROFIT2_KOTLIN_COROUTINES = "0.9.2"
    const val LOGGING_INTERCEPTOR = "4.9.1"
    const val VIEW_BINDING_PROPERTY_DELEGATE = "1.4.7"
    const val CICERONE = "7.0"
    const val GLIDE = "4.12.0"
    const val ROOM = "2.3.0"
    const val ESPRESSO = "3.4.0"
    const val JUNIT = "4.13.2"
    const val ANDROIDX_JUNIT = "1.1.3"
    const val ANDROIDX_CORE_KTX = "1.6.0"
    const val ANDROIDX_APPCOMPAT = "1.3.1"
    const val ANDROID_MATERIAL = "1.4.0"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "2.1.1"
    const val ANDROIDX_LEGACY_SUPPORT = "1.0.0"
    const val ANDROIDX_ANNOTATION = "1.2.0"
    const val MOCKITO = "4.0.0"
    const val COROUTINES_TEST = "1.4.2"
    const val CORE_TESTING = "2.1.0"
}

object Gradle {
    const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_GRADLE_PLUGIN}"
}

object Design {
    const val CORE = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINT_LAYOUT}"
    const val LEGACY = "androidx.legacy:legacy-support-v4:${Versions.ANDROIDX_LEGACY_SUPPORT}"
    const val ANNOTATION = "androidx.annotation:annotation:${Versions.ANDROIDX_ANNOTATION}"
}

object TestImpl {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.MOCKITO}"
    const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:${Versions.MOCKITO}"
    const val KOTLINX_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST}"
    const val CORE_TESTING = "androidx.arch.core:core-testing:${Versions.CORE_TESTING}"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT2}"
    const val RETROFIT_COROUTINES = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.RETROFIT2_KOTLIN_COROUTINES}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR}"
}

object ViewBinding {
    const val VIEW_BINDING_PROPERTY_DELEGATE = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.VIEW_BINDING_PROPERTY_DELEGATE}"
}

object Koin {
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
}

object Kotlin {
    const val KOTLINX_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
    const val KOTLINX_COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
    const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_VIEW_MODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
}

object Navigation {
    const val CICERONE = "com.github.terrakok:cicerone:${Versions.CICERONE}"
}

object ImageLoaders {
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
}

object Room {
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
}
