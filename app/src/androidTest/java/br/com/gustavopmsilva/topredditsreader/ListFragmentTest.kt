package br.com.gustavopmsilva.topredditsreader

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gustavopmsilva.topredditsreader.ui.list.ListFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class ListFragmentTest : KoinTest {

    private lateinit var scenario: FragmentScenario<ListFragment>

    @Test
    fun whenOpenFragment_checkViewsAreDisplayed() {
        scenario = launchFragmentInContainer()

        onView(withId(R.id.srl_refresh)).check(matches(isDisplayed()))
        onView(withId(R.id.rcv_posts)).check(matches(isDisplayed()))
    }
}
