package ru.androidlearning.model

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.androidlearning.model.repository.DictionaryRepository
import ru.androidlearning.model.repository.DictionaryRepositoryImpl
import ru.androidlearning.model.repository.datasource.cloud.DictionaryDataSourceCloud
import ru.androidlearning.model.repository.datasource.local.DictionaryDataSourceLocal

class DictionaryRepositoryImplTest {
    companion object {
        const val SEARCH_QUERY = "test string"
    }

    @Mock
    private lateinit var dictionaryDataSourceCloud: DictionaryDataSourceCloud

    @Mock
    private lateinit var dictionaryDataSourceLocal: DictionaryDataSourceLocal

    private val dictionaryRepository: DictionaryRepository by lazy { DictionaryRepositoryImpl(dictionaryDataSourceCloud, dictionaryDataSourceLocal) }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun search_online() {
        val isOnline = true
        val searchResult = listOf(mock(SearchDto::class.java))
        runBlocking {
            `when`(dictionaryDataSourceCloud.search(SEARCH_QUERY)).thenReturn(searchResult)
            dictionaryRepository.search(SEARCH_QUERY, isOnline)

            val inOrder = inOrder(dictionaryDataSourceCloud, dictionaryDataSourceLocal)

            inOrder.verify(dictionaryDataSourceCloud, times(1)).search(SEARCH_QUERY)
            inOrder.verify(dictionaryDataSourceLocal, times(1)).retain(searchResult)
            verify(dictionaryDataSourceLocal, never()).search(anyString())
        }
    }

    @Test
    fun search_offline() {
        val isOnline = false
        val searchResult = listOf(mock(SearchDto::class.java))
        runBlocking {
            `when`(dictionaryDataSourceLocal.search(SEARCH_QUERY)).thenReturn(searchResult)
            dictionaryRepository.search(SEARCH_QUERY, isOnline)
            verify(dictionaryDataSourceCloud, never()).search(SEARCH_QUERY)
            verify(dictionaryDataSourceLocal, never()).retain(searchResult)
            verify(dictionaryDataSourceLocal, times(1)).search(anyString())
        }
    }

    @Test
    fun getHistory_test() {
        runBlocking {
            dictionaryRepository.getHistory()
            verify(dictionaryDataSourceLocal, times(1)).getHistory()
        }
    }
}
