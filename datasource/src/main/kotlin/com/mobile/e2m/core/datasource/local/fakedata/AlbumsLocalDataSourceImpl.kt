package com.mobile.e2m.core.datasource.local.fakedata

import com.mobile.e2m.core.datasource.local.fakedata.database.fakeAlbumsData
import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel

class AlbumsLocalDataSourceImpl : AlbumsLocalDataSource {
    override suspend fun getAlbums(): List<AlbumsModel> = fakeAlbumsData()
}
