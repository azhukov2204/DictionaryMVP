package ru.androidlearning.dictionary.ui.fragments.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import ru.androidlearning.dictionary.R
import ru.androidlearning.dictionary.databinding.FragmentDetailsBinding
import ru.androidlearning.dictionary.ui.DictionaryPresentationData

private const val ARG_PARAM_TRANSLATED_WORD = "TranslatedWord"

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val translatedWord: DictionaryPresentationData.TranslatedWord? by lazy { arguments?.getParcelable(ARG_PARAM_TRANSLATED_WORD) }
    private val viewBinding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)
    private val router: Router by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        showData()
    }

    private fun initToolbar() {
        (context as AppCompatActivity).apply {
            setSupportActionBar(viewBinding.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setDisplayShowTitleEnabled(true)
            }
        }
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            router.exit()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showData() {
        with(viewBinding) {
            translatedWord?.let { translatedWord ->
                wordTextView.text = translatedWord.word
                wordMeaningTextView.text = translatedWord.meaning
                wordTranscriptionTextView.text = translatedWord.transcription
            }
        }
        loadImage()
    }

    private fun loadImage() {
        with(viewBinding) {
            translatedWord?.imageUrl?.let { imageUrl ->
                Glide.with(requireContext())
                    .load("https:$imageUrl")
                    .thumbnail(
                        Glide.with(requireContext())
                            .load(R.drawable.loading_gif)
                    )
                    .listener(
                        object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                imageView.setImageResource(R.drawable.no_photo_error)
                                return true
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }
                        }
                    )
                    .into(imageView)
            }
        }
    }

    companion object {
        fun newInstance(translatedWord: DictionaryPresentationData.TranslatedWord) =
            DetailsFragment().apply {
                arguments = bundleOf(ARG_PARAM_TRANSLATED_WORD to translatedWord)
            }
    }
}
