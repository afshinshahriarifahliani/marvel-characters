package com.afshinshahriarifahliani.marvel_characters.data.model.characters


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Series(
    @SerializedName("available")
    val available: Int,

):Serializable