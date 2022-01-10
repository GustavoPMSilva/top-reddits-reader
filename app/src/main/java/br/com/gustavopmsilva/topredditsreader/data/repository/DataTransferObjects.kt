package br.com.gustavopmsilva.topredditsreader.data.repository

import br.com.gustavopmsilva.topredditsreader.data.api.NetworkPost
import br.com.gustavopmsilva.topredditsreader.data.api.NetworkTopPostsResponse
import br.com.gustavopmsilva.topredditsreader.data.database.DatabasePostData
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.data.domain.PostList

/*fun List<DatabasePostData>.asDomainModel(): List<NetworkPost> =
    map {
        NetworkPost(
            NetworkPostData(
                it.id,
                it.title,
                it.thumbnail,
                it.isVideo,
                it.url?.let { url ->
                    NetworkPostPreview(
                        listOf(
                            NetworkPostImage(
                                NetworkPostImageSource(url)
                            )
                        )
                    )
                }
            )
        )
    }*/

fun NetworkTopPostsResponse.asDatabaseModel(): Array<DatabasePostData> {
    return data.posts.map {
        DatabasePostData(
            it.data.id,
            it.data.title,
            it.data.thumbnail,
            it.data.isVideo,
            it.data.preview?.images?.get(0)?.source?.url
        )
    }.toTypedArray()
}

fun List<NetworkPost>.asDomainModel(): List<Post> =
    map {
        Post(
            it.data.id,
            it.data.title,
            it.data.thumbnail,
            it.data.isVideo,
            it.data.preview?.images?.get(0)?.source?.url
        )
    }

fun NetworkTopPostsResponse.asDomainModel(): PostList {
    return PostList(data.after, data.posts.asDomainModel())
}
