package com.afshinshahriarifahliani.marvel_characters.data.model.characters
data class ItemDetailsResult(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail
) {
    data class Thumbnail(val path: String, val extension: String)
}