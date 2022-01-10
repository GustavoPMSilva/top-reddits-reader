package br.com.gustavopmsilva.topredditsreader.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: String,
    val title: String,
    val thumbnail: String,
    val image: String?,
    val permalink: String,
    val ups: Long,
    val downs: Long,
    val subredditName: String,
    val author: String,
    val numComments: Int,
    val postHint: String?
) : Parcelable
