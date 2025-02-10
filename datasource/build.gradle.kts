plugins {
    id("data-source-module")
}

android {
    namespace = "com.mobile.e2m.core.datasource"
}

dependencies {
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}
