package br.com.gustavopmsilva.topredditsreader.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DatabasePostData(
    val title: String,
    @PrimaryKey val thumbnail: String
)
