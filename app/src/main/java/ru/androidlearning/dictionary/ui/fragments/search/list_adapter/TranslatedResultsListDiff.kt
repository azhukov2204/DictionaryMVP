package ru.androidlearning.dictionary.ui.fragments.search.list_adapter

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

object TranslatedResultsListDiff : DiffUtil.ItemCallback<DictionaryPresentationData.TranslatedWord>() {
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
