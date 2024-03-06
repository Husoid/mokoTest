plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.buildconfig)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Core"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            // работа с сетью
            implementation(libs.ktor.android)
            //implementation(libs.security.crypto)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            // навигация
            implementation(libs.odyssey.core) // For core classes
            implementation(libs.odyssey.compose)
            // DI
            api(libs.kodein)
            // сереализация gson
            api(libs.kotlin.serialization.core)
            // корутины
            api(libs.kotlin.coroutines.core)
            // работа с сетью
            api(libs.ktor.core)
            implementation(libs.bundles.ktor)
            // Secure key-value storage
            api(libs.kvault)
            // ViewModel для KMP
            api(libs.bundles.kviewmodel)
        }
        iosMain.dependencies {
            // работа с сетью
            implementation(libs.ktor.ios)
        }
    }
}

android {
    namespace = "com.tisonline.core"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.version.get()
    }

    multiplatformResources {
        resourcesPackage = "com.tisonline.core"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        commonMainApi(libs.bundles.moko.resources)
    }
}

buildConfig {
    useKotlinOutput { internalVisibility = false }
    buildConfigField<String>("TOKEN_APP", "91D409E4C13A433E8791FC9CD8A43925")
    forClass("KtorSetting") {
        buildConfigField<String>("URL", "https://demo-pvdp.tis-online.com/pvdp/api/v1")
    }
}

