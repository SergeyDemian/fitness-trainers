
rootProject.name = "fitness-trainers"

pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val kotestVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion apply false
        id("io.kotest.multiplatform") version kotestVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false
    }
}

include(
    "home-work-1",
    "fitness-trainers-api-v1",
    "fitness-trainers-api-v2",
    "fitness-trainers-common",
    "fitness-trainers-mappers-v1",
    "fitness-trainers-app"
)