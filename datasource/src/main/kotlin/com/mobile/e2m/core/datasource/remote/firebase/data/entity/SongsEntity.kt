package com.mobile.e2m.core.datasource.remote.firebase.data.entity

import androidx.annotation.Keep

@Keep
data class SongsEntity(
    val id: String = "",
    val name: String? = null,
    val singer: String? = null,
    val songUrl: String? = null,
    val imageUrl: String? = null
) {
    constructor() : this("", null, null, null, null)
}
