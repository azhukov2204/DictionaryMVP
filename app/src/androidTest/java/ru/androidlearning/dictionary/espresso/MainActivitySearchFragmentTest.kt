package ru.androidlearning.dictionary.espresso

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.androidlearning.dictionary.R
import ru.androidlearning.dictionary.ui.activity.MainActivity
import ru.androidlearning.presentation.fragments.search.TranslatedResultsListAdapter

@RunWith(AndroidJUnit4::class)
class MainActivitySearchFragmentTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun searchFragment_ScrollToPosition() {
        loadList()
        onView(withId(R.id.translation_results_recycler_view))
            .perform(
                RecyclerViewActions.scrollToPosition<TranslatedResultsListAdapter.TranslatedResultsViewHolder>(0)
            )
    }

    @Test
    fun searchFragment_ScrollToDescendantItem() {
        loadList()
        onView(withId(R.id.translation_results_recycler_view))
            .perform(
                RecyclerViewActions.scrollTo<TranslatedResultsListAdapter.TranslatedResultsViewHolder>(
                    hasDescendant(withText(WORD_IN_RECYCLER_VIEW))
                )
            )
    }

    @Test
    fun searchFragment_PerformClickAtPosition() {
        loadList()
        onView(withId(R.id.translation_results_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<TranslatedResultsListAdapter.TranslatedResultsViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun searchFragment_PerformClickAtDescendantItem() {
        loadList()
        onView(withId(R.id.translation_results_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<TranslatedResultsListAdapter.TranslatedResultsViewHolder>(
                    hasDescendant(withText(WORD_IN_RECYCLER_VIEW)),
                    click()
                )
            )
    }

    @After
    fun close() {
        scenario.close()
    }

    private fun loadList() {
        onView(withId(R.id.enter_word_edit_text)).perform(click())
        onView(withId(R.id.enter_word_edit_text)).perform(replaceText(SEARCH_WORD))
        onView(isRoot()).perform(delay())
    }

    private fun delay(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = ON_DELAY_MESSAGE
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(DELAY)
            }
        }
    }

    companion object {
        private const val SEARCH_WORD = "Garlic"
        private const val WORD_IN_RECYCLER_VIEW = "garlic"
        private const val DELAY = 3000L
        private const val ON_DELAY_MESSAGE = "Wait for ${DELAY / 1000} seconds"
    }
}
