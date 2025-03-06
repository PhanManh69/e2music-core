package com.mobile.e2m.service.di

import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.mobile.e2m.service.exoplayer.MusicService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@UnstableApi
val serviceModule = module {

    single { MusicService() }

    single {
        ExoPlayer.Builder(androidContext()).build()
    }

    single {
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    single {
        SimpleExoPlayer.Builder(androidContext()).build().apply {
            setAudioAttributes(get(), true)
            setHandleAudioBecomingNoisy(true)
        }
    }

    single<DataSource.Factory> {
        DefaultDataSource.Factory(androidContext())
    }
}
