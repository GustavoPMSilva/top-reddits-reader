package br.com.gustavopmsilva.topredditsreader.data.api

import br.com.gustavopmsilva.topredditsreader.data.model.TopPostsResponse
import retrofit2.http.GET

interface PostsApi {

    @GET("top.json")
    suspend fun fetchTop(): TopPostsResponse
}
