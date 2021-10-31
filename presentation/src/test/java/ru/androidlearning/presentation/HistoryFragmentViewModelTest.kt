package ru.androidlearning.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import ru.androidlearning.core.DataLoadingState
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.presentation.fragments.history.HistoryFragmentViewModel
import ru.androidlearning.presentation.interactor.Interactor

class HistoryFragmentViewModelTest {
    companion object {
        const val DELAY_MS = 500L
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: Interactor

    private val historyFragmentViewModel by lazy { HistoryFragmentViewModel(interactor) }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getHistory_startDataLoading() {
        historyFragmentViewModel.getHistory()
        assertTrue(historyFragmentViewModel.getLiveData().value is DataLoadingState.Loading)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Test
    fun getHistory_searchSuccess() {
        val dictionaryPresentationDataModel = mock(DictionaryPresentationDataModel::class.java)
        runBlockingTest {
            `when`(interactor.getHistory()).thenReturn(dictionaryPresentationDataModel)
            historyFragmentViewModel.getHistory()
        }
        Thread.sleep(DELAY_MS)
        assertEquals(DataLoadingState.Success(dictionaryPresentationDataModel), historyFragmentViewModel.getLiveData().value)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @Test
    fun getHistory_searchError() {
        val exception = Error()
        runBlockingTest {
            `when`(interactor.getHistory()).thenThrow(exception)
            historyFragmentViewModel.getHistory()
        }
        Thread.sleep(DELAY_MS)
        assertEquals(DataLoadingState.Error<DictionaryPresentationDataModel>(exception), historyFragmentViewModel.getLiveData().value)
    }
}
