package br.com.gustavopmsilva.topredditsreader.data.api

import com.squareup.moshi.Json

data class NetworkPostData(
    val id: String,
    val title: String,
    val thumbnail: String,
    @Json(name = "is_video") val isVideo: Boolean,
    val preview: NetworkPostPreview?
)
