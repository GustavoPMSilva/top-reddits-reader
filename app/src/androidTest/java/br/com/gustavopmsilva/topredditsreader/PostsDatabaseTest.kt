package br.com.gustavopmsilva.topredditsreader

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gustavopmsilva.topredditsreader.data.database.DatabasePostData
import br.com.gustavopmsilva.topredditsreader.data.database.PostsDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsDatabaseTest {

    private lateinit var database: PostsDatabase

    private val mockedDatabasePostData = DatabasePostData(
        id = "id",
        title = "title",
        thumbnail = "thumbnail",
        image = "image",
        permalink = "permalink",
        ups = 0,
        downs = 0,
        subredditName = "subredditName",
        author = "author",
        numComments = 0,
        isImagePost = false
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, PostsDatabase::class.java).build()
    }

    @Test
    fun whenInsertPost_shouldReturnPost() {
        database.postDataDao.insertAll(mockedDatabasePostData)
        val postList = database.postDataDao.getPosts()
        assertTrue(postList.size == 1)
        assertEquals(mockedDatabasePostData, postList[0])
    }

    @After
    fun closeDb() {
        database.close()
    }
}
