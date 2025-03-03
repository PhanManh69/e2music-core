package com.mobile.e2m.core.datasource.local.fakedata

import com.mobile.e2m.core.datasource.local.fakedata.model.SongsModel

interface SongsLocalDataSource {
    suspend fun getSongs(): List<SongsModel>
}
