package ru.androidlearning.presentation.fragments

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object InstantSearchFlow {
    private val mutableSearchStateFlow = MutableStateFlow("")
    val searchFlow: Flow<String> = mutableSearchStateFlow

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            mutableSearchStateFlow.value = s.toString()
        }
    }

    fun beginObserveEditTextChanges(textInputEditText: EditText) {
        textInputEditText.addTextChangedListener(textWatcher)
    }

    fun endObserveEditTextChanges(textInputEditText: EditText) {
        textInputEditText.removeTextChangedListener(textWatcher)
    }
}
