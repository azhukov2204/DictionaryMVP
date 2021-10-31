package ru.androidlearning.core

import org.junit.Assert
import org.junit.Test
import ru.androidlearning.model.Meanings
import ru.androidlearning.model.SearchDto
import ru.androidlearning.model.Translation

class DictionaryPresentationDataModelMapperTest {

    private val currentTimeMs = System.currentTimeMillis()

    private val meaningsOne = Meanings(
        id = 1,
        partOfSpeechCode = "n",
        translation = Translation(
            text = "TranslatedWord",
            note = "Test note"
        ),
        previewUrl = "//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w=96&h=72",
        imageUrl = "//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w=640&h=480",
        transcription = "ˈteɪbl",
        soundUrl = "//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=table"
    )

    private val meaningsTwo = Meanings(
        id = 2,
        partOfSpeechCode = "n",
        translation = Translation(
            text = "TranslatedWord2",
            note = "Test note2"
        ),
        previewUrl = "//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w=96&h=72",
        imageUrl = "//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w=640&h=480",
        transcription = "ˈteɪbl",
        soundUrl = "//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=table"
    )

    private val searchDtoActual = SearchDto(
        id = 100,
        word = "TestWord",
        meanings = listOf(meaningsOne, meaningsTwo),
        savedTime = currentTimeMs
    )

    private val dictionaryPresentationDataModelExpected = DictionaryPresentationDataModel(
        translatedWords = listOf(
            DictionaryPresentationDataModel.TranslatedWord(
                id = 100,
                word = "TestWord",
                meaning = "TranslatedWord, TranslatedWord2",
                transcription = "ˈteɪbl",
                imageUrl = "//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w=640&h=480",
                savedTime = currentTimeMs
            )
        )
    )

    private val dictionaryPresentationDataModelNotExpected = DictionaryPresentationDataModel(
        translatedWords = listOf(
            DictionaryPresentationDataModel.TranslatedWord(
                id = 200,
                word = "TestWord",
                meaning = "TranslatedWord, TranslatedWord2",
                transcription = "ˈteɪbl",
                imageUrl = "//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w=640&h=480",
                savedTime = currentTimeMs
            )
        )
    )

    @Test
    fun dictionaryPresentationDataModel_Mapper_Equals() {
        Assert.assertEquals(
            dictionaryPresentationDataModelExpected,
            DictionaryPresentationDataModel.Mapper.map(listOf(searchDtoActual))
        )
    }

    @Test
    fun dictionaryPresentationDataModel_Mapper_NotEquals() {
        Assert.assertNotEquals(
            dictionaryPresentationDataModelNotExpected,
            DictionaryPresentationDataModel.Mapper.map(listOf(searchDtoActual))
        )
    }

    @Test
    fun dictionaryPresentationDataModel_Mapper_ArrayEquals() {
        Assert.assertArrayEquals(
            dictionaryPresentationDataModelExpected.translatedWords?.toTypedArray(),
            DictionaryPresentationDataModel.Mapper.map(listOf(searchDtoActual)).translatedWords?.toTypedArray()
        )
    }

    @Test
    fun dictionaryPresentationDataModel_Mapper_assertNull() {
        val searchDto = SearchDto(
            id = null,
            word = null,
            meanings = null,
            savedTime = null
        )
        Assert.assertNull(
            DictionaryPresentationDataModel.Mapper.map(listOf(searchDto)).translatedWords?.first()?.id
        )
    }

    @Test
    fun dictionaryPresentationDataModel_Mapper_assertNotNull() {
        val searchDto = SearchDto(
            id = null,
            word = null,
            meanings = null,
            savedTime = null
        )
        Assert.assertNotNull(
            DictionaryPresentationDataModel.Mapper.map(listOf(searchDto))
        )
    }

    @Test
    fun dictionaryPresentationDataModel_Mapper_assertNotSame() {
        Assert.assertNotSame(
            dictionaryPresentationDataModelExpected,
            DictionaryPresentationDataModel.Mapper.map(listOf(searchDtoActual))
        )
    }
}
