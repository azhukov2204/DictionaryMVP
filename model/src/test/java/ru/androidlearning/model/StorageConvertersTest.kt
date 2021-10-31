package ru.androidlearning.model

import org.junit.Assert
import org.junit.Test
import ru.androidlearning.model.repository.datasource.local.storage.StorageConverters

class StorageConvertersTest {

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

    private val meaningsOriginal = listOf(meaningsOne, meaningsTwo)
    private val jsonOriginal =
        "[{\"id\":1,\"partOfSpeechCode\":\"n\",\"translation\":{\"text\":\"TranslatedWord\",\"note\":\"Test note\"},\"previewUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d96\\u0026h\\u003d72\",\"imageUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d640\\u0026h\\u003d480\",\"transcription\":\"ˈteɪbl\",\"soundUrl\":\"//d2fmfepycn0xw0.cloudfront.net?gender\\u003dmale\\u0026accent\\u003dbritish\\u0026text\\u003dtable\"},{\"id\":2,\"partOfSpeechCode\":\"n\",\"translation\":{\"text\":\"TranslatedWord2\",\"note\":\"Test note2\"},\"previewUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d96\\u0026h\\u003d72\",\"imageUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d640\\u0026h\\u003d480\",\"transcription\":\"ˈteɪbl\",\"soundUrl\":\"//d2fmfepycn0xw0.cloudfront.net?gender\\u003dmale\\u0026accent\\u003dbritish\\u0026text\\u003dtable\"}]"

    private val storageConverters by lazy { StorageConverters() }

    @Test
    fun storageConverters_directlyConverts_assertEquals() {
        val json = storageConverters.fromMeaningsToJson(meaningsOriginal)
        val meaningsExpected = storageConverters.fromJsonToMeanings(json)
        Assert.assertEquals(meaningsExpected, meaningsOriginal)
    }

    @Test
    fun storageConverters_backConverts_assertEquals() {
        val meanings = storageConverters.fromJsonToMeanings(jsonOriginal)
        val jsonExpected = storageConverters.fromMeaningsToJson(meanings)
        Assert.assertEquals(jsonExpected, jsonOriginal)
    }

    @Test
    fun storageConverters_fromJsonToMeanings_assertEquals() {
        val meaningsExpected = storageConverters.fromJsonToMeanings(jsonOriginal)
        Assert.assertEquals(meaningsExpected, meaningsOriginal)
    }

    @Test
    fun storageConverters_fromMeaningsToJson_assertEquals() {
        val jsonExpected = storageConverters.fromMeaningsToJson(meaningsOriginal)
        Assert.assertEquals(jsonExpected, jsonOriginal)
    }

    @Test
    fun storageConverters_fromJsonToMeanings_assertNotEquals() {
        val json =
            "[{\"id\":2,\"partOfSpeechCode\":\"n\",\"translation\":{\"text\":\"TranslatedWord\",\"note\":\"Test note\"},\"previewUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d96\\u0026h\\u003d72\",\"imageUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d640\\u0026h\\u003d480\",\"transcription\":\"ˈteɪbl\",\"soundUrl\":\"//d2fmfepycn0xw0.cloudfront.net?gender\\u003dmale\\u0026accent\\u003dbritish\\u0026text\\u003dtable\"},{\"id\":2,\"partOfSpeechCode\":\"n\",\"translation\":{\"text\":\"TranslatedWord2\",\"note\":\"Test note2\"},\"previewUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d96\\u0026h\\u003d72\",\"imageUrl\":\"//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w\\u003d640\\u0026h\\u003d480\",\"transcription\":\"ˈteɪbl\",\"soundUrl\":\"//d2fmfepycn0xw0.cloudfront.net?gender\\u003dmale\\u0026accent\\u003dbritish\\u0026text\\u003dtable\"}]"
        val meaningsExpected = storageConverters.fromJsonToMeanings(json)
        Assert.assertNotEquals(meaningsExpected, meaningsOriginal)
    }

    @Test
    fun storageConverters_fromMeaningsToJson_assertNotEquals() {
        val meanings = listOf(meaningsOne)
        val jsonExpected = storageConverters.fromMeaningsToJson(meanings)
        Assert.assertNotEquals(jsonExpected, jsonOriginal)
    }
}
