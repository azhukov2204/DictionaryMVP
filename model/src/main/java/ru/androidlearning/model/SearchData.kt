package ru.androidlearning.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_data", indices = [Index("word")])
data class SearchData(
    @PrimaryKey
    @ColumnInfo(name = "id") @SerializedName("id") val id: Int?,
    @ColumnInfo(name = "word") @SerializedName("text") val word: String?,
    @ColumnInfo(name = "meanings") @SerializedName("meanings") val meanings: List<Meanings>?,
    @ColumnInfo(name = "saved_time") var savedTime: Long?
)

data class Meanings(
    @SerializedName("id") val id: Int?,
    @SerializedName("partOfSpeechCode") val partOfSpeechCode: String?,
    @SerializedName("translation") val translation: Translation?,
    @SerializedName("previewUrl") val previewUrl: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("transcription") val transcription: String?,
    @SerializedName("soundUrl") val soundUrl: String?
)

data class Translation(
    @SerializedName("text") val text: String?,
    @SerializedName("note") val note: String?
)
