package br.com.gustavopmsilva.topredditsreader.data.repository

import br.com.gustavopmsilva.topredditsreader.data.database.DatabasePostData
import br.com.gustavopmsilva.topredditsreader.data.model.Post
import br.com.gustavopmsilva.topredditsreader.data.model.PostData
import br.com.gustavopmsilva.topredditsreader.data.model.TopPostsResponse

fun List<DatabasePostData>.asDomainModel(): List<Post> =
    map { Post(PostData(it.id, it.title, it.thumbnail)) }

fun TopPostsResponse.asDatabaseModel(): Array<DatabasePostData> {
    return data.posts.map {
        DatabasePostData(it.data.id, it.data.title, it.data.thumbnail)
    }.toTypedArray()
}
