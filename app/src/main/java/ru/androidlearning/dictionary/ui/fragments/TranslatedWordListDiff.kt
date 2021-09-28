package ru.androidlearning.dictionary.ui.fragments

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

object TranslatedWordListDiff : DiffUtil.ItemCallback<DictionaryPresentationData.TranslatedWord>() {
    override fun areItemsTheSame(
        oldItem: DictionaryPresentationData.TranslatedWord,
        newItem: DictionaryPresentationData.TranslatedWord
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: DictionaryPresentationData.TranslatedWord,
        newItem: DictionaryPresentationData.TranslatedWord
    ): Boolean =
        oldItem == newItem
}
