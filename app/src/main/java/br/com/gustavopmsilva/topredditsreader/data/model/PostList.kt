package br.com.gustavopmsilva.topredditsreader.data.model

import com.squareup.moshi.Json

data class PostList(val after: String, @Json(name = "children") var posts: List<Post>)
