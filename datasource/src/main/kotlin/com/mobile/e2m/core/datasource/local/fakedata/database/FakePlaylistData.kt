package com.mobile.e2m.core.datasource.local.fakedata.database

import com.mobile.e2m.core.datasource.local.fakedata.model.PlaylistsModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

fun fakePlaylistData(): PersistentList<PlaylistsModel> {
    val itemsList = persistentListOf(
        PlaylistsModel(
            id = 0,
            avatar = "https://lh3.googleusercontent.com/KmxAsJHOpaGZgUnXolBVc9VF9xxn1EXcY_Usk-KfcIyQJ6QiVRYEEZiJKShMM_DI4EzpIJ-rw241wNYm=w544-h544-l90-rj",
            username = "PhanManh",
            name = "List nhạc Chill",
            songs = fakeSongsData().filter { it.id in setOf(0, 4, 6, 8, 9) }.toPersistentList(),
        ),
        PlaylistsModel(
            id = 1,
            avatar = "https://lh3.googleusercontent.com/9fE20eMGF4KeEmzNLgba9buDFfTIs68bj1l4U8F-jIKiSv-QoSOMlobhHfy0-puH8ly4-XyAyK1iI5s=w544-h544-l90-rj",
            username = "PhanManh",
            name = "Nhạc đi ngủ",
            songs = fakeSongsData().filter { it.id in setOf(3, 2, 1, 5, 7, 9) }.toPersistentList(),
        ),
        PlaylistsModel(
            id = 2,
            avatar = "https://i.ytimg.com/vi/A_DsyE_7SLM/hq720.jpg?sqp=-oaymwEXCNUGEOADIAQqCwjVARCqCBh4INgESFo&rs=AMzJL3nx2rHJa3uWJusnusDTMfz4GD_kIg",
            username = "PhanManh",
            name = "Nhạc sad",
            songs = fakeSongsData().filter { it.id in setOf(7, 3, 5, 2) }.toPersistentList(),
        ),
        PlaylistsModel(
            id = 3,
            avatar = "https://lh3.googleusercontent.com/Erh42SO2L7fpCw22bp8ViFyWgiCbwnU4k-66eiO3DaV2YQ_DUBsd1KUoo4mVmGWNAOni3wBGsayyNuE=w544-h544-l90-rj",
            username = "PhanManh",
            name = "Nhạc Rap",
            songs = fakeSongsData().filter { it.id in setOf(4, 0, 6, 8, 9) }.toPersistentList(),
        ),
    )

    return itemsList
}
