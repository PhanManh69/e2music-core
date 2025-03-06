package com.mobile.e2m.core.datasource.remote.firebase.data

import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity

@OptIn(UnstableApi::class)
class SongsFirebaseDataSourceImpl(
    private val firebaseMusicSource: FirebaseDataSource,
) : SongsFirebaseDataSource {
    override suspend fun getAllSongs(): List<SongsEntity> = firebaseMusicSource.getAllSongs()

    override suspend fun getSongByMediaItem(mediaItem: MediaItem?): SongsEntity? =
        firebaseMusicSource.getSongByMediaItem(mediaItem)

    override suspend fun fetchMediaData() = firebaseMusicSource.fetchMediaData()
}
