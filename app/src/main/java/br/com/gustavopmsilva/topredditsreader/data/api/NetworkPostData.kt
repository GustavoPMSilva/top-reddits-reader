package br.com.gustavopmsilva.topredditsreader.data.api

import com.squareup.moshi.Json

data class NetworkPostData(
    val id: String,
    val title: String,
    val thumbnail: String,
    @Json(name = "url_overridden_by_dest") val image: String?,
    val permalink: String,
    val ups: Long,
    val downs: Long,
    @Json(name = "subreddit_name_prefixed") val subredditName: String,
    val author: String,
    @Json(name = "num_comments") val numComments: Int,
    @Json(name = "post_hint") val postHint: String?
)
