package ru.androidlearning.model.repository.datasource.local

import ru.androidlearning.model.SearchData
import ru.androidlearning.model.repository.datasource.local.storage.DictionaryStorage

class DictionaryDataSourceLocalImpl(
    private val dictionaryStorage: DictionaryStorage
) : DictionaryDataSourceLocal {
    override suspend fun retain(searchData: SearchData) {
        searchData
            .apply { savedTime = System.currentTimeMillis() }
            .let { searchDataSavedTime ->
                dictionaryStorage
                    .dictionaryDao()
                    .retain(searchDataSavedTime)
            }
    }

    override suspend fun retain(searchDataList: List<SearchData>) {
        searchDataList
            .onEach { searchData -> searchData.savedTime = System.currentTimeMillis() }
            .let { searchDataListSavedTime ->
                dictionaryStorage
                    .dictionaryDao()
                    .retain(searchDataListSavedTime)
            }
    }

    override suspend fun search(word: String): List<SearchData> =
        dictionaryStorage
            .dictionaryDao()
            .getWords(word)

    override suspend fun getHistory(): List<SearchData> =
        dictionaryStorage
            .dictionaryDao()
            .getAllWords()
}
