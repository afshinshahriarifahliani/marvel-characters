package com.afshinshahriarifahliani.marvel_characters.data.model.characters


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val characters: List<MarvelCharacter>,
    @SerializedName("total")
    val total: Int
)