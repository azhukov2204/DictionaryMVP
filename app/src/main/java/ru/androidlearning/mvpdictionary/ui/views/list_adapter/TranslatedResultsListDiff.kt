package ru.androidlearning.mvpdictionary.ui.views.list_adapter

import androidx.recyclerview.widget.DiffUtil

object TranslatedResultsListDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}
