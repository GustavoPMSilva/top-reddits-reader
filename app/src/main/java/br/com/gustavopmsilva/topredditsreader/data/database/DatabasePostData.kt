package br.com.gustavopmsilva.topredditsreader.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabasePostData(
    @PrimaryKey val id: String,
    val title: String,
    val thumbnail: String,
    val image: String?,
    val permalink: String,
    val ups: Long,
    val downs: Long,
    val subredditName: String,
    val author: String,
    val numComments: Int,
    val isImagePost: Boolean
)
