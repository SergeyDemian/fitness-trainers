plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":fitness-trainers-api-v1"))
    implementation(project(":fitness-trainers-common"))

    testImplementation(kotlin("test-junit"))
}