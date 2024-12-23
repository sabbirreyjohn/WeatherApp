plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    kotlin("kapt")
    kotlin(libs.plugins.kotlinx.serialization.get().pluginId)
    alias(libs.plugins.ksp)

}

android {
    namespace = "xyz.androidrey.weatherapp.home"
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(projects.network)
    implementation(projects.theme)
    implementation(projects.common)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.navigation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.constraintlayout.compose)
    implementation(libs.kotlinx.serialization)

    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.testing)
    kapt(libs.hilt.android.kapt)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler.kapt)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)

    implementation(libs.onetapcompose)
    implementation(libs.coil)

    implementation(libs.androidx.datastore)

    implementation(libs.androidx.datastore.preferences)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.mockk)
    testImplementation (libs.kotlinx.coroutines.test.v190)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}