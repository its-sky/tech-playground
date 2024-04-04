plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "test"
include("module-api")
include("module-common")
include("module-domain")
include("module-external")
include("module-notification")
