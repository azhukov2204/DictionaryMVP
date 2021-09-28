package ru.androidlearning.dictionary.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.androidlearning.dictionary.data.SearchData

@Parcelize
data class DictionaryPresentationData(
    val translatedWords: List<TranslatedWord?>?
) : Parcelable {
    object Mapper {
        fun map(searchDataList: List<SearchData>): DictionaryPresentationData =
            DictionaryPresentationData(
                searchDataList.map { searchData ->
                    TranslatedWord(
                        id = searchData.id,
                        word = searchData.word,
                        meaning = convertSearchDataToMeaningsLine(searchData)
                    )
                }
            )

        private fun convertSearchDataToMeaningsLine(searchData: SearchData): String? {
            val separator = ", "
            return searchData.meanings
                ?.map { meaning -> meaning.translation?.text }
                ?.joinToString(separator = separator)
        }
    }

    @Parcelize
    data class TranslatedWord(
        val id: Int?,
        val word: String?,
        val meaning: String?
    ) : Parcelable
}
