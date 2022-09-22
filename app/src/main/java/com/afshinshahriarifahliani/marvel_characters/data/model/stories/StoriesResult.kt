package com.afshinshahriarifahliani.marvel_characters.data.model.stories
data class StoriesResult(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: ComicThumbnail
) {
    data class ComicThumbnail(val path: String, val extension: String)
}