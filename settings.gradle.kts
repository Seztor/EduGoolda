pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "EduGoolda"

include(":app")
include(":core")
include(":data:auth")
include(":features:root")
include(":features:group")
include(":data:group")
include(":data:general")
include(":features:auth")
include(":data:user")
include(":features:home")
include(":data:home")
include(":data:join_requests")
include(":features:profile")
include(":features:join_requests")
include(":features:lesson")
include(":data:lesson")
include(":data:solutions")
include(":features:solution")

include(":features:main")
