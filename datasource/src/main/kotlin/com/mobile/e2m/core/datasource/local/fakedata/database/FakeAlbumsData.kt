package com.mobile.e2m.core.datasource.local.fakedata.database

import com.mobile.e2m.core.datasource.local.fakedata.model.AlbumsModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

fun fakeAlbumsData(): PersistentList<AlbumsModel> {
    val itemsList = persistentListOf(
        AlbumsModel(
            id = 0,
            avatar = "https://lh3.googleusercontent.com/zUR9aaX2AWqeXDvKssUEBn0O1bf8JZdg8e2HyXRMwf6bBFl25LrLrbqktriYxOAmOCgrh4mk5kSDT5vd=w544-h544-l90-rj",
            name = "Bật nó lên",
            singer = "Soobin",
            songs = fakeSongsData().filter { it.id in setOf(3, 7) }.toPersistentList(),
        ),
        AlbumsModel(
            id = 1,
            avatar = "https://lh3.googleusercontent.com/lMCahfUBxLZ6TAf3zOVgKoA7mVF7RHe4pxLB2LJWgButeLwgnvqd0cdtrV-ZxvCQeTCidHcyVin99ejv=w1920-h800-p-l90-rj",
            name = "Nhạc của Đen",
            singer = "Đen Vâu",
            songs = fakeSongsData().filter { it.id in setOf(8, 9) }.toPersistentList(),
        ),
    )

    return itemsList
}
