package ru.androidlearning.presentation.espresso

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.fragments.R
import ru.androidlearning.presentation.fragments.details.DetailsFragment

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTests {

    @Before
    fun launchFragment() {
        val fragmentArgs = bundleOf(ARG_PARAM_TRANSLATED_WORD to testData)
        launchFragmentInContainer<DetailsFragment>(fragmentArgs, fragmentTheme)
    }

    @Test
    fun fragment_CheckWordTextView() {
        val assertion = ViewAssertions.matches(ViewMatchers.withText(testData.word))
        onView(withId(R.id.word_text_view)).check(assertion)
    }

    @Test
    fun fragment_CheckWordTranscriptionTextView() {
        val assertion = ViewAssertions.matches(ViewMatchers.withText(testData.transcription))
        onView(withId(R.id.word_transcription_text_view)).check(assertion)
    }

    @Test
    fun fragment_CheckWordMeaningTextView() {
        val assertion = ViewAssertions.matches(ViewMatchers.withText(testData.meaning))
        onView(withId(R.id.word_meaning_text_view)).check(assertion)
    }

    companion object {
        private const val ARG_PARAM_TRANSLATED_WORD = "TranslatedWord"
        private val fragmentTheme = R.style.Theme_MaterialComponents_Light_NoActionBar

        private val testData = DictionaryPresentationDataModel.TranslatedWord(
            id = 1,
            word = "Hello",
            meaning = "Приветствие",
            transcription = "Hello",
            imageUrl = "http://testdata.com",
            savedTime = null
        )
    }
}
