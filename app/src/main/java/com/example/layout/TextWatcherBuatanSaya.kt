package com.example.layout

import addDot
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

class TextWatherBuatanSaya(val textInput: TextInputEditText) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(s: Editable?) {
        /* Mengatur penambahan titik (.) pada setiap 3 digit */
        textInput.removeTextChangedListener(this)
        val text = s.toString().replace(".", "")
        val formattedText = addDot(text)
        textInput.setText(formattedText)
        textInput.setSelection(formattedText.length)
        textInput.addTextChangedListener(this)
    }
}