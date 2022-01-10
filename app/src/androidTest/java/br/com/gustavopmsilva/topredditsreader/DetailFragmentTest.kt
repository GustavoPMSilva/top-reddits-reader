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
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest : KoinTest {

    private lateinit var scenario: FragmentScenario<DetailFragment>

    @Test
    fun whenPostWithPositiveVotes_checkDataIsDisplayedCorrectly() {
        val mockedPostWithPositiveVotes = Post(
            id = "id",
            title = "title",
            thumbnail = "thumbnail",
            image = "image",
            permalink = "/permalink",
            ups = 25,
            downs = 0,
            subredditName = "subredditName",
            author = "author",
            numComments = 20,
            isImagePost = true
        )

        scenario = launchFragmentInContainer(bundleOf("post" to mockedPostWithPositiveVotes))

        onView(withId(R.id.tv_title)).check(matches(withText(mockedPostWithPositiveVotes.title)))
        onView(withId(R.id.iv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_arrow_up)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_votes)).check(matches(withText(mockedPostWithPositiveVotes.ups.toString())))
        onView(withId(R.id.iv_arrow_down)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_num_comments)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_num_comments)).check(matches(withText(mockedPostWithPositiveVotes.numComments.toString())))
        onView(withId(R.id.tv_label_author)).check(matches(withText("by")))
        onView(withId(R.id.tv_author)).check(matches(withText(mockedPostWithPositiveVotes.author)))
        onView(withId(R.id.btn_open)).check(matches(isDisplayed()))
    }

    @Test
    fun whenPostWithNegativeVotes_checkDataIsDisplayedCorrectly() {
        val mockedPostWithNegativeVotes = Post(
            id = "id",
            title = "title",
            thumbnail = "thumbnail",
            image = "image",
            permalink = "/permalink",
            ups = 0,
            downs = 50,
            subredditName = "subredditName",
            author = "author",
            numComments = 30,
            isImagePost = true
        )

        scenario = launchFragmentInContainer(bundleOf("post" to mockedPostWithNegativeVotes))

        onView(withId(R.id.tv_title)).check(matches(withText(mockedPostWithNegativeVotes.title)))
        onView(withId(R.id.iv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_arrow_up)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_votes)).check(matches(withText("-${mockedPostWithNegativeVotes.downs}")))
        onView(withId(R.id.iv_arrow_down)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_num_comments)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_num_comments)).check(matches(withText(mockedPostWithNegativeVotes.numComments.toString())))
        onView(withId(R.id.tv_label_author)).check(matches(withText("by")))
        onView(withId(R.id.tv_author)).check(matches(withText(mockedPostWithNegativeVotes.author)))
        onView(withId(R.id.btn_open)).check(matches(isDisplayed()))
    }

    @Test
    fun whenPostWithZeroVotes_checkDataIsDisplayedCorrectly() {
        val mockedPostWithZeroVotes = Post(
            id = "id",
            title = "title",
            thumbnail = "thumbnail",
            image = "image",
            permalink = "/permalink",
            ups = 0,
            downs = 0,
            subredditName = "subredditName",
            author = "author",
            numComments = 152,
            isImagePost = true
        )

        scenario = launchFragmentInContainer(bundleOf("post" to mockedPostWithZeroVotes))

        onView(withId(R.id.tv_title)).check(matches(withText(mockedPostWithZeroVotes.title)))
        onView(withId(R.id.iv_image)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_arrow_up)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_votes)).check(matches(withText("0")))
        onView(withId(R.id.iv_arrow_down)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_num_comments)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_num_comments)).check(matches(withText(mockedPostWithZeroVotes.numComments.toString())))
        onView(withId(R.id.tv_label_author)).check(matches(withText("by")))
        onView(withId(R.id.tv_author)).check(matches(withText(mockedPostWithZeroVotes.author)))
        onView(withId(R.id.btn_open)).check(matches(isDisplayed()))
    }
}
