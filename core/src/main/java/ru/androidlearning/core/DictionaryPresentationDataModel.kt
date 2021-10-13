package ru.androidlearning.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.androidlearning.model.SearchDto

@Parcelize
data class DictionaryPresentationDataModel(
    val translatedWords: List<TranslatedWord?>?
) : Parcelable {
    object Mapper {
        fun map(searchDtoList: List<SearchDto>): DictionaryPresentationDataModel =
            DictionaryPresentationDataModel(
                searchDtoList.map { searchData ->
                    TranslatedWord(
                        id = searchData.id,
                        word = searchData.word,
                        meaning = convertSearchDataToMeaningsLine(searchData),
                        transcription = getTranscription(searchData),
                        imageUrl = getImageUrl(searchData),
                        savedTime = searchData.savedTime
                    )
                }
            )

        private fun convertSearchDataToMeaningsLine(searchDto: SearchDto): String? {
            val separator = ", "
            return searchDto.meanings
                ?.map { meaning -> meaning.translation?.text }
                ?.joinToString(separator = separator)
        }

        private fun getTranscription(searchDto: SearchDto): String? =
            searchDto.meanings
                ?.takeIf { it.isNotEmpty() }
                ?.first()
                ?.transcription

        private fun getImageUrl(searchDto: SearchDto): String? =
            searchDto.meanings
                ?.takeIf { it.isNotEmpty() }
                ?.first()
                ?.imageUrl
                ?.takeIf { it.isNotBlank() }
    }

    @Parcelize
    data class TranslatedWord(
        val id: Int?,
        val word: String?,
        val meaning: String?,
        val transcription: String?,
        val imageUrl: String?,
        val savedTime: Long?
    ) : Parcelable
}
