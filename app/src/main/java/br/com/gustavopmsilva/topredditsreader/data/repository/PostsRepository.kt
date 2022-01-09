package br.com.gustavopmsilva.topredditsreader.data.repository

import br.com.gustavopmsilva.topredditsreader.core.base.Resource
import br.com.gustavopmsilva.topredditsreader.core.extension.toFlowResource
import br.com.gustavopmsilva.topredditsreader.data.api.PostsApi
import br.com.gustavopmsilva.topredditsreader.data.model.TopPostsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsRepository(private val postsApi: PostsApi) {

    suspend fun fetchTop(after: String): Flow<Resource<TopPostsResponse>> = flow {
        val topPostsResponse = postsApi.fetchTop(after)
        topPostsResponse.data.posts =
            topPostsResponse.data.posts.filter { it.data.thumbnail != "default" }
        emit(topPostsResponse)
    }.toFlowResource()
}
