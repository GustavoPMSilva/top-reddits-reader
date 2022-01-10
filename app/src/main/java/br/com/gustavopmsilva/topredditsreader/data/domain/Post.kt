package br.com.gustavopmsilva.topredditsreader.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: String,
    val title: String,
    val thumbnail: String,
    val isVideo: Boolean,
    val url: String?
) : Parcelable
