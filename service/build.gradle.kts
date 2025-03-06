plugins {
    id("core-ui-module")
}

android {
    namespace = "com.mobile.e2m.core.service"
}

dependencies {
    implementation(vc.bundleFirebase)
    implementation(vc.bundleExoPlayer)
    implementation(vc.concurrentFutures)

    implementation(project(mapOf("path" to ":core:ui")))
    implementation(project(mapOf("path" to ":core:datasource")))
}
