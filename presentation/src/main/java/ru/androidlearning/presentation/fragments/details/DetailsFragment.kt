package ru.androidlearning.presentation.fragments.details

import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.inject
import ru.androidlearning.core.DictionaryPresentationDataModel
import ru.androidlearning.fragments.R
import ru.androidlearning.utils.delegates.viewById

private const val ARG_PARAM_TRANSLATED_WORD = "TranslatedWord"

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val translatedWord: DictionaryPresentationDataModel.TranslatedWord? by lazy { arguments?.getParcelable(ARG_PARAM_TRANSLATED_WORD) }
    private val router: Router by inject()
    private val toolbar by viewById<Toolbar>(R.id.toolbar)
    private val wordTextView by viewById<TextView>(R.id.word_text_view)
    private val wordMeaningTextView by viewById<TextView>(R.id.word_meaning_text_view)
    private val wordTranscriptionTextView by viewById<TextView>(R.id.word_transcription_text_view)
    private val imageView by viewById<ImageView>(R.id.image_view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViews()
        showData()
    }

    private fun initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val blurEffect = RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.MIRROR)
            imageView.setOnClickListener {
                imageView.setRenderEffect(blurEffect)
            }
        }
    }

    private fun initToolbar() {
        (context as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
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
        translatedWord?.let { translatedWord ->
            wordTextView.text = translatedWord.word
            wordMeaningTextView.text = translatedWord.meaning
            wordTranscriptionTextView.text = translatedWord.transcription
        }
        loadImage()
    }

    private fun loadImage() {
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

    companion object {
        fun newInstance(translatedWord: DictionaryPresentationDataModel.TranslatedWord) =
            DetailsFragment().apply {
                arguments = bundleOf(ARG_PARAM_TRANSLATED_WORD to translatedWord)
            }
    }
}
