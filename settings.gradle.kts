rootProject.name = "Eshop"

pluginManagement {
    repositories {
//        mavenCentral()
//        mavenLocal()
//        gradlePluginPortal()
        maven { url = uri("https://maven.myket.ir/") }
    }
}

dependencyResolutionManagement {
    repositories {
//        mavenLocal()
//        mavenCentral()
//        google()
        maven { url = uri("https://maven.myket.ir/") }
    }
    versionCatalogs {
        create("ktorLibs") {
            from(files("gradle/ktor-version-catalog-3.5.0.toml"))
        }
    }
}
