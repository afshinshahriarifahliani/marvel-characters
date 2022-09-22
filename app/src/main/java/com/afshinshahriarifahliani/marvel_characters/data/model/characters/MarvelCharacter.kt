package com.afshinshahriarifahliani.marvel_characters.data.model.characters


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "characters_table")
data class MarvelCharacter(
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("resourceURI")
    val resourceURI: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("modified")
    val modified: String = "",

    @SerializedName("comics")
    val comics: Comics,

    @SerializedName("events")
    val events: Events,

    @SerializedName("series")
    val series: Series,

    @SerializedName("stories")
    val stories: Stories,

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,

    @SerializedName("urls")
    val urls: List<Url>
) : Serializable