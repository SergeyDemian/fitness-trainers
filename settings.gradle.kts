
rootProject.name = "fitness-trainers"

pluginManagement {
    val kotlinVersion: String by settings
    val kotestVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        id("io.kotest.multiplatform") version kotestVersion apply false
    }
}

include(
    "home-work-1",
    "fitness-trainers-app"
)