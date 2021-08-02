buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0")
    }
}

val apiCode by extra(93)

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("Delete", Delete::class) {
    delete(rootProject.buildDir)
}
