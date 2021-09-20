package ru.androidlearning.dictionary.ui

import ru.androidlearning.dictionary.data.DictionaryData

data class DictionaryPresentationData(
    val word: String?,
    val translatedWords: List<String?>?
) {
    object Mapper {
        fun map(dictionaryData: DictionaryData): DictionaryPresentationData =
            dictionaryData.def?.takeIf { defList ->
                defList.isNotEmpty()
            }?.first().let { def ->
                DictionaryPresentationData(
                    word = def?.word,
                    translatedWords = def?.translated?.map { it.translatedWord }
                )
            }
    }
}
