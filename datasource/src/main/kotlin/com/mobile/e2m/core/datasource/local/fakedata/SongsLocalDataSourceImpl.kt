package com.mobile.e2m.core.datasource.local.fakedata

import com.mobile.e2m.core.datasource.local.fakedata.database.fakeSongsData
import com.mobile.e2m.core.datasource.local.fakedata.model.SongsModel

class SongsLocalDataSourceImpl : SongsLocalDataSource {
    override suspend fun getSongs(): List<SongsModel> = fakeSongsData()
}
