package com.mobile.e2m.core.datasource.remote.firebase.data.database

import android.util.Log
import androidx.annotation.Keep
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.mobile.e2m.core.datasource.other.DatabaseConfig.SONGS_TABLE
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import kotlinx.coroutines.tasks.await

@Keep
class MusicDatabase(private val db: FirebaseFirestore) {

    suspend fun getAllSongs(): List<SongsEntity> {
        return try {
            val snapshot: QuerySnapshot = db.collection(SONGS_TABLE).get().await()
            snapshot.documents.mapNotNull { it.toObject(SongsEntity::class.java) }
        } catch (e: Exception) {
            Log.e("EManh Debug", "Bug get song: ${e.message}", e)
            emptyList()
        }
    }

    suspend fun addSong(song: SongsEntity): Result<Unit> {
        return try {
            db.collection(SONGS_TABLE)
                .document(song.id)
                .set(song)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("EManh Debug", "Bug add song: ${e.message}", e)
            Result.failure(e)
        }
    }
}
