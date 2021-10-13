package ru.androidlearning.model.repository.datasource.local

import ru.androidlearning.model.SearchDto
import ru.androidlearning.model.repository.datasource.local.storage.DictionaryStorage

class DictionaryDataSourceLocalImpl(
    private val dictionaryStorage: DictionaryStorage
) : DictionaryDataSourceLocal {
    override suspend fun retain(searchDto: SearchDto) {
        searchDto
            .apply { savedTime = System.currentTimeMillis() }
            .let { searchDataSavedTime ->
                dictionaryStorage
                    .dictionaryDao()
                    .retain(searchDataSavedTime)
            }
    }

    override suspend fun retain(searchDtoList: List<SearchDto>) {
        searchDtoList
            .onEach { searchData -> searchData.savedTime = System.currentTimeMillis() }
            .let { searchDataListSavedTime ->
                dictionaryStorage
                    .dictionaryDao()
                    .retain(searchDataListSavedTime)
            }
    }

    override suspend fun search(word: String): List<SearchDto> =
        dictionaryStorage
            .dictionaryDao()
            .getWords(word)

    override suspend fun getHistory(): List<SearchDto> =
        dictionaryStorage
            .dictionaryDao()
            .getAllWords()
}
