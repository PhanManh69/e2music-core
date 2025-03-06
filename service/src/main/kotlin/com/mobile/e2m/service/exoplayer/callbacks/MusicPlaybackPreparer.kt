package com.mobile.e2m.service.exoplayer.callbacks

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.Player
import com.mobile.e2m.core.datasource.remote.firebase.data.FirebaseDataSource

@androidx.media3.common.util.UnstableApi
class MusicPlaybackPreparer(
    private val firebaseMusicSource: FirebaseDataSource,
    private val player: ExoPlayer
) : Player.Listener {

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        firebaseMusicSource.whenReady { isReady ->
            if (isReady) {
                if (player.mediaItemCount == 0) {
                    player.setMediaItems(firebaseMusicSource.asMediaItems())
                    player.prepare()
                }
                if (isPlaying) {
                    player.play()
                } else {
                    player.pause()
                }
            }
        }
    }
}
