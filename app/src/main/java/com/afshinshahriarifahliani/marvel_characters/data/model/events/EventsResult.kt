package com.afshinshahriarifahliani.marvel_characters.data.model.events
data class EventsResult(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: ComicThumbnail
) {
    data class ComicThumbnail(val path: String, val extension: String)
}