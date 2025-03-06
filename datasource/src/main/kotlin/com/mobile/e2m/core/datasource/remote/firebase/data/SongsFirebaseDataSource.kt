package com.mobile.e2m.core.datasource.remote.firebase.data

import androidx.media3.common.MediaItem
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity

interface SongsFirebaseDataSource {
    suspend fun getAllSongs(): List<SongsEntity>

    suspend fun getSongByMediaItem(mediaItem: MediaItem?): SongsEntity?

    suspend fun fetchMediaData()
}
