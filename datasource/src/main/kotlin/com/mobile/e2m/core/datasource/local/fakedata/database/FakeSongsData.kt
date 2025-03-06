package com.mobile.e2m.core.datasource.local.fakedata.database

import com.mobile.e2m.core.datasource.local.fakedata.model.SongsModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

fun fakeSongsData(): PersistentList<SongsModel> {
    val itemsList = persistentListOf(
        SongsModel(
            id = 0,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/KmxAsJHOpaGZgUnXolBVc9VF9xxn1EXcY_Usk-KfcIyQJ6QiVRYEEZiJKShMM_DI4EzpIJ-rw241wNYm=w544-h544-l90-rj",
            name = "2AM",
            singer = "JustaTee - BigDaddy",
            numberLikes = 2402,
            views = 9283,
            genres = fakeGenresData().filter { it.id in setOf(4, 5, 7) }.toPersistentList(),
        ),
        SongsModel(
            id = 1,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/Qmh3Jo9MBakiKDtfRZP6jhpgsQWuO0-Wh-JaRQDsEcVpTHQUhq1PLRbCERWXBgWtmg5DzkmQWtuZrtKd6A=w544-h544-l90-rj",
            name = "Ánh nắng của anh",
            singer = "Đức Phúc",
            numberLikes = 1923,
            views = 10920,
            genres = fakeGenresData().filter { it.id in setOf(4, 7) }.toPersistentList(),
        ),
        SongsModel(
            id = 2,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/ptSXANqzjDIXHlhy6usQZ0Rvbxy2GEFbQ0gHZcqploPGy9OLo5gbb_wc4yVLXs-Tk8VYJaqydOPvEsYVEg=w544-h544-l90-rj",
            name = "Ngày em đẹp nhất",
            singer = "Tama",
            numberLikes = 1023,
            views = 8729,
            genres = fakeGenresData().filter { it.id in setOf(5, 7) }.toPersistentList(),
        ),
        SongsModel(
            id = 3,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/9fE20eMGF4KeEmzNLgba9buDFfTIs68bj1l4U8F-jIKiSv-QoSOMlobhHfy0-puH8ly4-XyAyK1iI5s=w544-h544-l90-rj",
            name = "Phía Sau Một Cô Gái",
            singer = "Soobin",
            numberLikes = 8723,
            views = 49203,
            genres = fakeGenresData().filter { it.id in setOf(4, 7) }.toPersistentList(),
        ),
        SongsModel(
            id = 4,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/Erh42SO2L7fpCw22bp8ViFyWgiCbwnU4k-66eiO3DaV2YQ_DUBsd1KUoo4mVmGWNAOni3wBGsayyNuE=w544-h544-l90-rj",
            name = "Sống cho hết đời thanh xuân 2",
            singer = "BCTM và TaynguyenSound",
            numberLikes = 2381,
            views = 19234,
            genres = fakeGenresData().filter { it.id in setOf(5) }.toPersistentList(),
        ),
        SongsModel(
            id = 5,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/YXUb8Reei3riqh9s0xO2m5TE9NF8txl5-mjN0XN0wwbVw-tKxfpd6q0LFJqqWAfpWnMBbtIhykQ_7NM=w544-h544-l90-rj",
            name = "Ngày mai người ta lấy chồng",
            singer = "Thành Đạt",
            numberLikes = 3912,
            views = 12029,
            genres = fakeGenresData().filter { it.id in setOf(4) }.toPersistentList(),
        ),
        SongsModel(
            id = 6,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/1u5cwy9cgfquRQrQCr71Q1Pu1NnC6IhZSYU5LlSm-z7cfSIhJuMbrhVr6-YQT4tsngtz7tIRvdDVZypz=w544-h544-l90-rj",
            name = "Già cùng nhau là được",
            singer = "Tùng, PC và TaynguyenSound",
            numberLikes = 2402,
            views = 9283,
            genres = fakeGenresData().filter { it.id in setOf(5) }.toPersistentList(),
        ),
        SongsModel(
            id = 7,
            linkSong = null,
            avatar = "https://i.ytimg.com/vi/A_DsyE_7SLM/hq720.jpg?sqp=-oaymwEXCNUGEOADIAQqCwjVARCqCBh4INgESFo&rs=AMzJL3nx2rHJa3uWJusnusDTMfz4GD_kIg",
            name = "Yêu Thương Ngày Đó",
            singer = "Soobin",
            numberLikes = 3812,
            views = 12933,
            genres = fakeGenresData().filter { it.id in setOf(4, 7) }.toPersistentList(),
        ),
        SongsModel(
            id = 8,
            linkSong = null,
            avatar = "https://i.ytimg.com/vi/UVbv-PJXm14/hq720.jpg?sqp=-oaymwEXCNUGEOADIAQqCwjVARCqCBh4INgESFo&rs=AMzJL3mB2RoY1Ctu1AvgD2LjvJcDAiWX1Q",
            name = "Mang tiền về cho mẹ",
            singer = "Đen Vâu",
            numberLikes = 4812,
            views = 21012,
            genres = fakeGenresData().filter { it.id in setOf(5) }.toPersistentList(),
        ),
        SongsModel(
            id = 9,
            linkSong = null,
            avatar = "https://lh3.googleusercontent.com/aF7yn-KQzQrDvdAJ3wZ9RaTu0YdTV0wpnFydubf6PZ7JSOFNp41hMljfR3kSjveyinqKUt6U219I6M9w=w544-h544-l90-rj",
            name = "Bài này chill phết",
            singer = "Đen Vâu",
            numberLikes = 5812,
            views = 22401,
            genres = fakeGenresData().filter { it.id in setOf(5, 7) }.toPersistentList(),
        ),
    )

    return itemsList
}
