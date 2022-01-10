package br.com.gustavopmsilva.topredditsreader.data.repository

import br.com.gustavopmsilva.topredditsreader.core.base.Resource
import br.com.gustavopmsilva.topredditsreader.core.extension.toFlowResource
import br.com.gustavopmsilva.topredditsreader.data.api.PostsApi
import br.com.gustavopmsilva.topredditsreader.data.database.PostsDatabase
import br.com.gustavopmsilva.topredditsreader.data.domain.PostList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PostsRepository(private val database: PostsDatabase, private val postsApi: PostsApi) {

    suspend fun fetchTop(after: String?): Flow<Resource<PostList>> = flow {
        if (after == null) {
            var databasePostList: PostList

            withContext(Dispatchers.IO) {
                val databasePosts = database.postDataDao.getPosts().asDomainModel()
                databasePostList = PostList("", databasePosts)
            }

            emit(databasePostList)
        }

        val topPostsResponse = postsApi.fetchTop(after)
        topPostsResponse.data.posts =
            topPostsResponse.data.posts.filter { it.data.thumbnail != "default" }

        withContext(Dispatchers.IO) {
            database.postDataDao.insertAll(*topPostsResponse.asDatabaseModel())
        }

        emit(topPostsResponse.asDomainModel())
    }.toFlowResource()
}
