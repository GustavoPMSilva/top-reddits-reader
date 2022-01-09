package br.com.gustavopmsilva.topredditsreader.data.api

import br.com.gustavopmsilva.topredditsreader.data.model.TopPostsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {

    @GET("top.json")
    suspend fun fetchTop(@Query("after") after: String): TopPostsResponse
}
