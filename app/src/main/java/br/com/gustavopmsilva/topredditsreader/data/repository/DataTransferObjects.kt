package br.com.gustavopmsilva.topredditsreader.data.repository

import br.com.gustavopmsilva.topredditsreader.data.api.NetworkPost
import br.com.gustavopmsilva.topredditsreader.data.api.NetworkTopPostsResponse
import br.com.gustavopmsilva.topredditsreader.data.database.DatabasePostData
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.data.domain.PostList

@JvmName("asDomainModelDatabasePostData")
fun List<DatabasePostData>.asDomainModel(): List<Post> =
    map {
        Post(
            it.id,
            it.title,
            it.thumbnail,
            it.image,
            it.permalink,
            it.ups,
            it.downs,
            it.subredditName,
            it.author,
            it.numComments,
            it.postHint
        )
    }

fun NetworkTopPostsResponse.asDatabaseModel(): Array<DatabasePostData> {
    return data.posts.map {
        DatabasePostData(
            it.data.id,
            it.data.title,
            it.data.thumbnail,
            it.data.image,
            it.data.permalink,
            it.data.ups,
            it.data.downs,
            it.data.subredditName,
            it.data.author,
            it.data.numComments,
            it.data.postHint
        )
    }.toTypedArray()
}

@JvmName("asDomainModelNetworkPost")
fun List<NetworkPost>.asDomainModel(): List<Post> =
    map {
        Post(
            it.data.id,
            it.data.title,
            it.data.thumbnail,
            it.data.image,
            it.data.permalink,
            it.data.ups,
            it.data.downs,
            it.data.subredditName,
            it.data.author,
            it.data.numComments,
            it.data.postHint
        )
    }

fun NetworkTopPostsResponse.asDomainModel(): PostList {
    return PostList(data.after, data.posts.asDomainModel())
}
