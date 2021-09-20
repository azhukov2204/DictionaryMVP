package ru.androidlearning.dictionary.data

import com.google.gson.annotations.SerializedName

data class DictionaryData(
    @SerializedName("def") val def: List<Def>?
)

data class Def(
    @SerializedName("text") val word: String?,
    @SerializedName("pos") val pos: String?,
    @SerializedName("gen") val gen: String?,
    @SerializedName("anm") val anm: String?,
    @SerializedName("tr") val translated: List<Translated>?
)

data class Translated (
    @SerializedName("text") val translatedWord : String?,
    @SerializedName("pos") val pos : String?,
    @SerializedName("fr") val fr : Int?,
)
