package com.afshinshahriarifahliani.marvel_characters.data.db

import androidx.room.TypeConverter
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.*
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): String {
        return thumbnail.path + thumbnail.extension
    }

    @TypeConverter
    fun toThumbnail(path: String): Thumbnail {
        val arr = path.split(".").toTypedArray()
        return Thumbnail(arr[0], arr[1])
    }

    @TypeConverter
    fun urlsToJson(value: List<Url>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToUrls(value: String) = Gson().fromJson(value, Array<Url>::class.java).toList()

    @TypeConverter
    fun fromComics(comics: Comics): Int {
        return comics.available
    }

    @TypeConverter
    fun toComics(available: Int): Comics {
        return Comics(available)
    }

    @TypeConverter
    fun fromSeries(series: Series): Int {
        return series.available
    }

    @TypeConverter
    fun toSeries(available: Int): Series {
        return Series(available)
    }

    @TypeConverter
    fun fromEvents(events: Events): Int {
        return events.available
    }

    @TypeConverter
    fun toEvents(available: Int): Events {
        return Events(available)
    }

    @TypeConverter
    fun fromStories(stories: Stories): Int {
        return stories.available
    }

    @TypeConverter
    fun toStories(available: Int): Stories {
        return Stories(available)
    }
}