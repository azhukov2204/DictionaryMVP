package ru.androidlearning.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.eq
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.androidlearning.core.DataLoadingState
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.presentation.fragments.search.dialog.SearchHistoryDialogViewModel
import ru.androidlearning.presentation.interactor.Interactor

class SearchHistoryDialogViewModelTest {
    companion object {
        const val SEARCH_QUERY = "test query"
        const val BLANK_QUERY = ""
        const val DELAY_MS = 500L
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: Interactor

    private val searchHistoryDialogViewModel by lazy { SearchHistoryDialogViewModel(interactor) }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun search_noSearchIfBlankQuery() {
        searchHistoryDialogViewModel.search(BLANK_QUERY)
        runBlocking {
            verify(interactor, never()).search(anyString(), anyBoolean())
        }
    }

    @Test
    fun search_invokeInteractorSearch() {
        searchHistoryDialogViewModel.search(SEARCH_QUERY)
        runBlocking {
            verify(interactor, times(1)).search(eq(SEARCH_QUERY), anyBoolean())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun search_searchSuccess() {
        val dictionaryPresentationDataModel = Mockito.mock(DictionaryPresentationDataModel::class.java)
        runBlockingTest {
            `when`(interactor.search(eq(SEARCH_QUERY), anyBoolean())).thenReturn(dictionaryPresentationDataModel)
            searchHistoryDialogViewModel.search(SEARCH_QUERY)
        }
        Thread.sleep(DELAY_MS)
        assertEquals(DataLoadingState.Success(dictionaryPresentationDataModel), searchHistoryDialogViewModel.getLiveData().value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun search_searchError() {
        val exception = Error()
        runBlockingTest {
            `when`(interactor.search(eq(SEARCH_QUERY), anyBoolean())).thenThrow(exception)
            searchHistoryDialogViewModel.search(SEARCH_QUERY)
        }
        Thread.sleep(DELAY_MS)
        assertEquals(DataLoadingState.Error<DictionaryPresentationDataModel>(exception), searchHistoryDialogViewModel.getLiveData().value)
    }
}
