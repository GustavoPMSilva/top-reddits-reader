package br.com.gustavopmsilva.topredditsreader.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabasePostData::class], version = 1)
abstract class PostsDatabase : RoomDatabase() {

    abstract val postDataDao: PostDataDao

    companion object {
        fun create(context: Context): PostsDatabase {
            return Room
                .databaseBuilder(context, PostsDatabase::class.java, "posts_database.db")
                .build()
        }
    }
}
