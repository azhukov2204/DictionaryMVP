package ru.androidlearning.dictionary.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.androidlearning.dictionary.data.DictionaryData

@Parcelize
data class DictionaryPresentationData(
    val word: String?,
    val translatedWords: List<String?>?,
) : Parcelable {
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
