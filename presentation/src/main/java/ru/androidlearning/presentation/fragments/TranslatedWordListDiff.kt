package ru.androidlearning.presentation.fragments

import androidx.recyclerview.widget.DiffUtil
import ru.androidlearning.core.DictionaryPresentationDataModel

object TranslatedWordListDiff : DiffUtil.ItemCallback<DictionaryPresentationDataModel.TranslatedWord>() {
    override fun areItemsTheSame(
        oldItem: DictionaryPresentationDataModel.TranslatedWord,
        newItem: DictionaryPresentationDataModel.TranslatedWord
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: DictionaryPresentationDataModel.TranslatedWord,
        newItem: DictionaryPresentationDataModel.TranslatedWord
    ): Boolean =
        oldItem == newItem
}
