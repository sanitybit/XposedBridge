plugins {
    id("com.android.library")
}

val apiCode: Int by rootProject.extra

android {
    compileSdk = 31
    buildToolsVersion = "31.0.0"

    defaultConfig {
        minSdk = 15
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

