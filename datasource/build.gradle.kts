plugins {
    id("data-source-module")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.mobile.e2m.core.datasource"
}

dependencies {
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(platform(vc.concurrentFirebaseBom))
    implementation(vc.bundleFirebase)
    implementation(vc.bundleExoPlayer)
    implementation(vc.concurrentFutures)
}
