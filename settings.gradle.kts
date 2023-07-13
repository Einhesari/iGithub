pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "iTollHub"
include(":app")
include(":core:designsystem")
include(":core:navigation")
include(":feature:search")
include(":core:network")
include(":core:data")
include(":core:model")
