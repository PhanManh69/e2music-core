package com.mobile.e2m.core.datasource.local.fakedata

import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel

interface AlbumsLocalDataSource {
    suspend fun getAlbums(): List<AlbumsModel>
}
