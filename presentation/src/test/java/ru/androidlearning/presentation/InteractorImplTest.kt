package ru.androidlearning.presentation

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.androidlearning.model.SearchDto
import ru.androidlearning.model.repository.DictionaryRepository
import ru.androidlearning.model.stopwatch.StopwatchOrchestrator
import ru.androidlearning.presentation.interactor.Interactor
import ru.androidlearning.presentation.interactor.InteractorImpl

class InteractorImplTest {

    @Mock
    private lateinit var dictionaryRepository: DictionaryRepository

    @Mock
    private lateinit var stopwatchOrchestrators: StopwatchOrchestrator

    private val interactor: Interactor by lazy { InteractorImpl(dictionaryRepository, stopwatchOrchestrators) }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getHistory_test() {
        val historyResult = listOf(mock(SearchDto::class.java))
        runBlocking {
            `when`(dictionaryRepository.getHistory()).thenReturn(historyResult)
            interactor.getHistory()
            verify(dictionaryRepository, times(1)).getHistory()
        }
    }

    @Test
    fun search_test() {
        val searchResult = listOf(mock(SearchDto::class.java))
        runBlocking {
            `when`(dictionaryRepository.search(anyString(), anyBoolean())).thenReturn(searchResult)
            interactor.search(anyString(), anyBoolean())
            verify(dictionaryRepository, times(1)).search(anyString(), anyBoolean())
        }
    }

    @Test
    fun getStopwatchOrchestrators_test() {
        val result = interactor.getStopwatchOrchestrators()
        assertArrayEquals(arrayOf(stopwatchOrchestrators), result)
    }
}
