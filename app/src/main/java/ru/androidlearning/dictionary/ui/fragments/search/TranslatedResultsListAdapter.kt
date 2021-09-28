package ru.androidlearning.dictionary.ui.fragments.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.dictionary.R
import ru.androidlearning.dictionary.databinding.TranslatedResultItemBinding
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.fragments.TranslatedWordListDiff

class TranslatedResultsListAdapter(private val onItemClick: (DictionaryPresentationData.TranslatedWord) -> Unit) :
    ListAdapter<DictionaryPresentationData.TranslatedWord, TranslatedResultsListAdapter.TranslatedResultsViewHolder>(TranslatedWordListDiff) {

    inner class TranslatedResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: TranslatedResultItemBinding by viewBinding(TranslatedResultItemBinding::bind)

        fun bind(translatedWord: DictionaryPresentationData.TranslatedWord) {
            with(viewBinding) {
                translatedWordTextView.text = translatedWord.word
                translatedWordMeaningsTextView.text = translatedWord.meaning
            }
            itemView.setOnClickListener { onItemClick(translatedWord) }
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
