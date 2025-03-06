package com.mobile.e2m.core.datasource.remote.firebase.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.mobile.e2m.core.datasource.remote.firebase.data.FirebaseDataSource
import com.mobile.e2m.core.datasource.remote.firebase.data.database.MusicDatabase
import org.koin.dsl.module

val firebaseModule = module {
    single { Firebase.firestore }

    single { MusicDatabase(get()) }

    single { FirebaseDataSource(get()) }
}
