package br.com.gustavopmsilva.topredditsreader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gustavopmsilva.topredditsreader.core.base.Resource
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.data.domain.PostList
import br.com.gustavopmsilva.topredditsreader.data.repository.PostsRepository
import br.com.gustavopmsilva.topredditsreader.ui.list.ListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewModelTest {

    private lateinit var viewModel: ListViewModel
    private val repository = mockk<PostsRepository>()

    private val mockedResponse =
        PostList(
            after = "after",
            listOf(
                Post(
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
            )
        )

    private val mockedImagePost = Post(
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
        isImagePost = true
    )

    private val mockedNonImagePost = Post(
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

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = ListViewModel(repository)
    }

    @Test
    fun whenFetchPostsWithLoading_shouldReturnLoadingTrue() {
        coEvery { repository.fetchTop(any()) } returns flowOf(Resource.Loading())

        viewModel.fetchPosts()

        viewModel.loading.observeForever {
            assertTrue(it)
        }
    }

    @Test
    fun whenFetchNextPostsWithLoading_shouldReturnLoadingTrue() {
        coEvery { repository.fetchTop(any()) } returns flowOf(Resource.Loading())

        viewModel.fetchNextPosts()

        viewModel.loading.observeForever {
            assertTrue(it)
        }
    }

    @Test
    fun whenFetchPostsWithSuccess_shouldReturnLoadingFalseAndPosts() {
        coEvery { repository.fetchTop(any()) } returns flowOf(Resource.Success(mockedResponse))

        viewModel.fetchPosts()

        viewModel.loading.observeForever {
            assertFalse(it)
        }

        viewModel.posts.observeForever {
            assertEquals(mockedResponse, it)
        }
    }

    @Test
    fun whenFetchNextPostsWithSuccess_shouldReturnLoadingFalseAndPosts() {
        coEvery { repository.fetchTop(any()) } returns flowOf(Resource.Success(mockedResponse))

        viewModel.fetchNextPosts()

        viewModel.loading.observeForever {
            assertFalse(it)
        }

        viewModel.posts.observeForever {
            assertEquals(mockedResponse, it)
        }
    }

    @Test
    fun whenFetchPostsWithError_shouldReturnLoadingFalse() {
        coEvery { repository.fetchTop(any()) } returns flowOf(Resource.Error(Exception("Error")))

        viewModel.fetchPosts()

        viewModel.loading.observeForever {
            assertFalse(it)
        }
    }

    @Test
    fun whenFetchNextPostsWithError_shouldReturnLoadingFalse() {
        coEvery { repository.fetchTop(any()) } returns flowOf(Resource.Error(Exception("Error")))

        viewModel.fetchNextPosts()

        viewModel.loading.observeForever {
            assertFalse(it)
        }
    }

    @Test
    fun whenImagePostClicked_shouldReturnPost() {
        viewModel.onPostClicked(mockedImagePost)

        viewModel.navigateToPostDetail.observeForever {
            assertEquals(mockedImagePost, it)
        }
    }

    @Test
    fun whenNonImagePostClicked_shouldReturnNull() {
        viewModel.onPostClicked(mockedNonImagePost)

        viewModel.navigateToPostDetail.observeForever {
            assertNull(it)
        }
    }
}
