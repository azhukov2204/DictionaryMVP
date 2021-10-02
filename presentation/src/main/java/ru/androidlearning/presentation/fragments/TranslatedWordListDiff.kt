package ru.androidlearning.presentation.fragments

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.core.DictionaryPresentationData

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
