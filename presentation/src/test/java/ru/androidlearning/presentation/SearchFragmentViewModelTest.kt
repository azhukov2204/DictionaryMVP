package ru.androidlearning.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.eq
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.androidlearning.core.DataLoadingState
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.presentation.fragments.search.SearchFragmentViewModel
import ru.androidlearning.presentation.interactor.Interactor
import ru.androidlearning.utils.network.NetworkStateMonitor

class SearchFragmentViewModelTest {
    companion object {
        const val SEARCH_QUERY = "test query"
        const val BLANK_QUERY = ""
        const val DELAY_MS = 500L
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: Interactor

    @Mock
    private lateinit var networkStateMonitor: NetworkStateMonitor

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @FlowPreview
    private val searchFragmentViewModel by lazy { SearchFragmentViewModel(interactor, networkStateMonitor, savedStateHandle) }

    @FlowPreview
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @FlowPreview
    @Test
    fun search_invokeInteractorSearch() {
        searchFragmentViewModel.search(SEARCH_QUERY)
        runBlocking {
            verify(interactor, times(1)).search(eq(SEARCH_QUERY), anyBoolean())
        }
    }

    @FlowPreview
    @Test
    fun search_noSearchIfBlankQuery() {
        searchFragmentViewModel.search(BLANK_QUERY)
        runBlocking {
            verify(interactor, never()).search(anyString(), anyBoolean())
        }
    }

    @FlowPreview
    @Test
    fun search_startDataLoading() {
        searchFragmentViewModel.search(SEARCH_QUERY)
        assertTrue(searchFragmentViewModel.getLiveData().value is DataLoadingState.Loading)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Test
    fun search_searchSuccess() {
        val dictionaryPresentationDataModel = mock(DictionaryPresentationDataModel::class.java)
        runBlockingTest {
            `when`(interactor.search(eq(SEARCH_QUERY), anyBoolean())).thenReturn(dictionaryPresentationDataModel)
            searchFragmentViewModel.search(SEARCH_QUERY)
        }
        Thread.sleep(DELAY_MS)
        assertEquals(DataLoadingState.Success(dictionaryPresentationDataModel), searchFragmentViewModel.getLiveData().value)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Test
    fun search_searchError() {
        val exception = Error()
        runBlockingTest {
            `when`(interactor.search(eq(SEARCH_QUERY), anyBoolean())).thenThrow(exception)
            searchFragmentViewModel.search(SEARCH_QUERY)
        }
        Thread.sleep(DELAY_MS)
        assertEquals(DataLoadingState.Error<DictionaryPresentationDataModel>(exception), searchFragmentViewModel.getLiveData().value)
    }
}
