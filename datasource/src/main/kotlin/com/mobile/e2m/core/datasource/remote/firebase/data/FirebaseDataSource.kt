package com.mobile.e2m.core.datasource.remote.firebase.data

import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.mobile.e2m.core.datasource.other.State
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.datasource.remote.firebase.data.database.MusicDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseDataSource (
    private val musicDatabase: MusicDatabase
) {

    var songs = emptyList<MediaItem>()
        private set

    private val onReadyListeners = mutableListOf<(Boolean) -> Unit>()

    private var songsEntityList = emptyList<SongsEntity>()
    private var state: State = State.STATE_CREATED
        set(value) {
            if (value == State.STATE_INITIALIZED || value == State.STATE_ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach { listener ->
                        listener(state == State.STATE_INITIALIZED)
                    }
                }
            } else {
                field = value
            }
        }

    suspend fun fetchMediaData() = withContext(Dispatchers.IO) {
        state = State.STATE_INITIALIZING
        try {
            val allSongs = musicDatabase.getAllSongs()
            songsEntityList = allSongs
            songs = allSongs.map { song ->
                MediaItem.Builder()
                    .setMediaId(song.id)
                    .setUri(song.songUrl)
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(song.name)
                            .setArtist(song.singer)
                            .setAlbumArtist(song.songUrl)
                            .setArtworkUri(song.imageUrl?.toUri())
                            .setIsPlayable(true)
                            .build()
                    )
                    .build()
            }
            state = State.STATE_INITIALIZED
        } catch (e: Exception) {
            state = State.STATE_ERROR
        }
    }

    fun getAllSongs(): List<SongsEntity> = songsEntityList

    fun asMediaItems(): List<MediaItem> = songs

    fun getSongByMediaItem(mediaItem: MediaItem?): SongsEntity? {
        return songsEntityList.find { it.id == mediaItem?.mediaId }
    }

    fun whenReady(action: (Boolean) -> Unit): Boolean {
        return if (state == State.STATE_CREATED || state == State.STATE_INITIALIZING) {
            onReadyListeners += action
            false
        } else {
            action(state == State.STATE_INITIALIZED)
            true
        }
    }
}
