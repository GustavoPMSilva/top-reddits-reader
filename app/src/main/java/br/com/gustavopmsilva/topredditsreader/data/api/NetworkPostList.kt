package br.com.gustavopmsilva.topredditsreader.data.api

import com.squareup.moshi.Json

data class NetworkPostList(var after: String?, @Json(name = "children") var posts: List<NetworkPost>)
