package ru.androidlearning.dictionary.ui.fragments.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.dictionary.R
import ru.androidlearning.dictionary.databinding.HistoryListItemBinding
import ru.androidlearning.dictionary.ui.DictionaryPresentationData
import ru.androidlearning.dictionary.ui.fragments.TranslatedWordListDiff
import java.text.SimpleDateFormat
import java.util.*

class HistoryListAdapter(private val onItemClick: (DictionaryPresentationData.TranslatedWord) -> Unit) :
    ListAdapter<DictionaryPresentationData.TranslatedWord, HistoryListAdapter.HistoryListViewHolder>(TranslatedWordListDiff) {

    inner class HistoryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: HistoryListItemBinding by viewBinding(HistoryListItemBinding::bind)

        fun bind(translatedWord: DictionaryPresentationData.TranslatedWord) {
            with(viewBinding) {
                val simpleDayFormat = SimpleDateFormat(root.context.getString(R.string.date_format), Locale.getDefault())
                translatedWordTextView.text = translatedWord.word
                translatedWordMeaningsTextView.text = translatedWord.meaning
                translatedWord.savedTime?.let { savedDate.text = simpleDayFormat.format(Date(it)) }
                itemView.setOnClickListener { onItemClick(translatedWord) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder =
        HistoryListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.history_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<DictionaryPresentationData.TranslatedWord?>?) {
        list?.sortedByDescending { it?.savedTime }
            .let { super.submitList(it) }
    }
}
