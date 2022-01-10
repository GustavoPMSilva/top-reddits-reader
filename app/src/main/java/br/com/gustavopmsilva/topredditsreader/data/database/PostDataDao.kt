package br.com.gustavopmsilva.topredditsreader.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDataDao {

    @Query("select * from databasepostdata")
    fun getPosts(): List<DatabasePostData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: DatabasePostData)
}
