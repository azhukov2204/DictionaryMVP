package ru.androidlearning.presentation.fragments.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.fragments.R
import ru.androidlearning.fragments.databinding.HistoryListItemBinding
import ru.androidlearning.presentation.fragments.TranslatedWordListDiff
import java.text.SimpleDateFormat
import java.util.*

class HistoryListAdapter(private val onItemClick: (DictionaryPresentationDataModel.TranslatedWord) -> Unit) :
    ListAdapter<DictionaryPresentationDataModel.TranslatedWord, HistoryListAdapter.HistoryListViewHolder>(TranslatedWordListDiff) {

    inner class HistoryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding: HistoryListItemBinding by viewBinding(HistoryListItemBinding::bind)

        fun bind(translatedWord: DictionaryPresentationDataModel.TranslatedWord) {
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

    override fun submitList(list: List<DictionaryPresentationDataModel.TranslatedWord?>?) {
        list?.sortedByDescending { it?.savedTime }
            .let { super.submitList(it) }
    }
}
