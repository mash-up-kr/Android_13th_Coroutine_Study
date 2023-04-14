plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)

    // Retrofit with Moshi Converter
    implementation(libs.retrofit)
    implementation(libs.converterMoshi)
    implementation(libs.converterScalar)
    implementation(libs.moshi)
    kapt(libs.moshiCodeGen)

    // OkHttp
    implementation(libs.okHttp)
    implementation(libs.okHttpInterceptor)

    // Coroutine
    implementation(libs.coroutine)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
