package br.com.gustavopmsilva.topredditsreader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.ui.detail.DetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val mockedPost = Post(
        id = "id",
        title = "title",
        thumbnail = "thumbnail",
        image = "image",
        permalink = "/permalink",
        ups = 0,
        downs = 0,
        subredditName = "subredditName",
        author = "author",
        numComments = 0,
        isImagePost = true
    )

    private val mockedUrl = "https://reddit.com/permalink"

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = DetailViewModel(mockedPost)
    }

    @Test
    fun shouldReturnPost() {
        viewModel.postData.observeForever {
            assertEquals(mockedPost, it)
        }
    }

    @Test
    fun whenOpenOnRedditClicked_shouldReturnUrl() {
        viewModel.openRedditInBrowser()

        viewModel.openUrl.observeForever {
            assertEquals(mockedUrl, it)
        }
    }

    @Test
    fun whenOpenOnRedditFinished_shouldReturnNull() {
        viewModel.openRedditInBrowser()
        viewModel.onOpenRedditInBrowserCompleted()

        viewModel.openUrl.observeForever {
            assertNull(it)
        }
    }
}
