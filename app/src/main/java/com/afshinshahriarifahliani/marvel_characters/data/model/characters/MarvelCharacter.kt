package com.afshinshahriarifahliani.marvel_characters.data.model.characters


import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "characters_table")
data class MarvelCharacter(
    @PrimaryKey()
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    @Embedded
    var thumbnail: Thumbnail = Thumbnail("", ""),
    @Embedded
    var comics: Comics = Comics(0),
    @Embedded
    var series: Series = Series(0),
    @Embedded
    var events: Events = Events(0),
    @Embedded
    var stories: Stories = Stories(0),
    @Ignore
    var urls: List<Url> = ArrayList(),
) : Parcelable {

    @Parcelize
    data class Thumbnail(val path: String, val extension: String): Parcelable

    @Parcelize
    data class Url(val type: String, val url: String): Parcelable

    @Parcelize
    data class Comics(@ColumnInfo(name = "comics_available") val available: Int): Parcelable

    @Parcelize
    data class Series(@ColumnInfo(name = "series_available") val available: Int): Parcelable

    @Parcelize
    data class Events(@ColumnInfo(name = "events_available") val available: Int): Parcelable

    @Parcelize
    data class Stories(@ColumnInfo(name = "stories_available") val available: Int): Parcelable

}