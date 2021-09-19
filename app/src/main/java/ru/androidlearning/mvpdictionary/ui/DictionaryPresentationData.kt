package ru.androidlearning.mvpdictionary.ui

import ru.androidlearning.mvpdictionary.data.DictionaryData

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
