plugins {
    id("com.android.library")
}

val apiCode: Int by rootProject.extra

android {
    compileSdk = 33
    buildToolsVersion = "33.0.3"

    defaultConfig {
        minSdk = 30
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
            buildConfigField("int", "API_CODE", "$apiCode")
            buildConfigField("boolean", "DEBUG", "false")
        }
        debug {
            buildConfigField("int", "API_CODE", "$apiCode")
            buildConfigField("boolean", "DEBUG", "true")
        }
    }
}

