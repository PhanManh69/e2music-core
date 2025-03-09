package com.mobile.e2m.service.exoplayer

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.media3.session.MediaSession
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSessionService
import com.mobile.e2m.core.datasource.remote.firebase.data.FirebaseDataSource
import com.mobile.e2m.service.exoplayer.callbacks.MusicPlaybackPreparer
import com.mobile.e2m.service.exoplayer.callbacks.MusicPlayerNotificationListener
import com.mobile.e2m.service.other.ServiceConfig.MUSIC_PLAYBACK
import com.mobile.e2m.service.other.ServiceConfig.NOTIFICATION_CHANNEL_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@androidx.media3.common.util.UnstableApi
class MusicService : MediaSessionService() {

    private val firebaseMusicSource: FirebaseDataSource by inject()
    private val exoPlayer: ExoPlayer by inject()

    private lateinit var mediaSession: MediaSession
    private lateinit var musicNotificationManager: MusicNotificationManager

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    var isForegroundService = false

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        mediaSession = MediaSession.Builder(this, exoPlayer)
            .setSessionActivity(getSessionActivityPendingIntent())
            .build()

        musicNotificationManager = MusicNotificationManager(
            this,
            mediaSession,
            MusicPlayerNotificationListener(this)
        )

        exoPlayer.addListener(MusicPlaybackPreparer(firebaseMusicSource, exoPlayer))

        serviceScope.launch {
            firebaseMusicSource.fetchMediaData()

            if (firebaseMusicSource.songs.isNotEmpty()) {
                exoPlayer.setMediaItems(firebaseMusicSource.asMediaItems())
                exoPlayer.prepare()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        exoPlayer.stop()
        exoPlayer.release()
        mediaSession.release()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_NOT_STICKY
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession {
        return mediaSession
    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopSelf()
        return super.onUnbind(intent)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                MUSIC_PLAYBACK,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                setSound(null, null)
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun getSessionActivityPendingIntent(): PendingIntent {
        val activityIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let {
            PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_IMMUTABLE)
        }
        return activityIntent ?: PendingIntent.getActivity(this, 0, Intent(), PendingIntent.FLAG_IMMUTABLE)
    }
}
