import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.wandok"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wandok"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", getApiKey())
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun getApiKey(): String {
    return gradleLocalProperties(rootDir).getProperty("api.key")
}

dependencies {
//    implementation(libs.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.compose.ui.tooling)
    androidTestImplementation(libs.compose.ui.manifest)
    androidTestImplementation(libs.compose.ui.junit4)
    implementation(libs.androidx.ktx)
    testImplementation(libs.androidx.junit)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.lifecycle.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.material)

    // compose - optional
    implementation(libs.compose.activity)
    implementation(libs.compose.viewmodel)
    implementation(libs.compose.livedata)
    implementation(libs.compose.rxjava2)

    // ui
    implementation(libs.navigation.compose)

    // liveData, viewModel, savedState, runtime-ktx
    implementation(libs.bundles.lifecycle)

    // network
    implementation(libs.bundles.network)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.gson)

    // di
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    // room
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    implementation(libs.preference)

    // image load
//    implementation(libs.glide)
//    implementation(libs.glide.compose)
    implementation(libs.coil.compose)
//    kapt(libs.glide.compiler)

    // loading indicator
    implementation(libs.spinkit)

    implementation(libs.timber)

    implementation(libs.bookContentParser)

    implementation(libs.paging3)
    implementation(libs.paging.compose)
}
