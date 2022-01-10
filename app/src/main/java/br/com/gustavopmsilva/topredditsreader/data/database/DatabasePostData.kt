package br.com.gustavopmsilva.topredditsreader.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DatabasePostData(
    @PrimaryKey val id: String,
    val title: String,
    val thumbnail: String,
    val isVideo: Boolean,
    val image: String?,
    val permalink: String,
    val ups: Long,
    val downs: Long,
    val subredditName: String,
    val author: String,
    val numComments: Int
)
