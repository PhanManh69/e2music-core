package com.mobile.e2m.core.datasource.local.fakedata.database

import com.mobile.e2m.core.datasource.local.fakedata.model.GenresModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

fun fakeGenresData(): PersistentList<GenresModel> {
    val itemsList = persistentListOf(
        GenresModel(id = 0, name = "Nhạc cổ điển"),
        GenresModel(id = 1, name = "Nhạc đồng quê"),
        GenresModel(id = 2, name = "Nhạc điện tử"),
        GenresModel(id = 3, name = "Nhạc Jazz và Blues"),
        GenresModel(id = 4, name = "Nhạc Pop"),
        GenresModel(id = 5, name = "Nhạc Rap và Hip-Hop"),
        GenresModel(id = 6, name = "Nhạc Rock"),
        GenresModel(id = 7, name = "Nhạc Soul và R&B"),
    )

    return itemsList
}
