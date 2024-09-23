//plugins {
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
//}
plugins {
    id("com.android.application")
    id("kotlin-android")
}



android {
    namespace = "com.pi12a082_akahorimoemi.kadai05_pi12_02"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pi12a082_akahorimoemi.kadai05_pi12_02"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("net.objecthunter:exp4j:0.4.8")
//    implementation("com.github.burnett01:kotlin-expression-builder:1.2.2")
}
