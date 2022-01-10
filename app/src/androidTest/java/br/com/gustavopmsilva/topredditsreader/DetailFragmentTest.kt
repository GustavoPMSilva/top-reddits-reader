package br.com.gustavopmsilva.topredditsreader

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gustavopmsilva.topredditsreader.data.domain.Post
import br.com.gustavopmsilva.topredditsreader.ui.detail.DetailFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest : KoinTest {

    private lateinit var scenario: FragmentScenario<DetailFragment>

    private val mockedPost = Post(
        id = "id",
        title = "title",
        thumbnail = "thumbnail",
        image = "image",
        permalink = "/permalink",
        ups = 25,
        downs = 0,
        subredditName = "subredditName",
        author = "author",
        numComments = 0,
        isImagePost = true
    )

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(bundleOf("post" to mockedPost))
    }

    @Test
    fun checkDataIsDisplayedCorrectly() {
        onView(withId(R.id.tv_title)).check(matches(withText(mockedPost.title)))
        onView(withId(R.id.iv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_arrow_up)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_votes)).check(matches(withText(mockedPost.ups.toString())))
        onView(withId(R.id.iv_arrow_down)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_num_comments)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_num_comments)).check(matches(withText(mockedPost.numComments.toString())))
        onView(withId(R.id.tv_label_author)).check(matches(withText("by")))
        onView(withId(R.id.tv_author)).check(matches(withText(mockedPost.author)))
        onView(withId(R.id.btn_open)).check(matches(isDisplayed()))
    }
}
