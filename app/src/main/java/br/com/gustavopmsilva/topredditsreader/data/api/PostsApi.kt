package br.com.gustavopmsilva.topredditsreader.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET("top.json")
    suspend fun fetchTop(@Query("after") after: String?): NetworkTopPostsResponse
}
