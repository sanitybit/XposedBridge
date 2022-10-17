buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
    }
}

val apiCode by extra(93)

tasks.register("Delete", Delete::class) {
    delete(rootProject.buildDir)
}
