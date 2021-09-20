package ru.androidlearning.dictionary.ui.views.list_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.dictionary.R
import ru.androidlearning.dictionary.databinding.TranslatedResultItemBinding

class TranslatedResultsListAdapter : ListAdapter<String, TranslatedResultsListAdapter.TranslatedResultsViewHolder>(TranslatedResultsListDiff) {

    inner class TranslatedResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: TranslatedResultItemBinding by viewBinding(TranslatedResultItemBinding::bind)

        fun bind(translatedWord: String) {
            viewBinding.translationResultTextView.text = translatedWord
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslatedResultsViewHolder =
        TranslatedResultsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.translated_result_item, parent, false)
        )

    override fun onBindViewHolder(holder: TranslatedResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
