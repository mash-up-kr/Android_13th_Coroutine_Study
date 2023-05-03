package com.example.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.Serializable

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

@Suppress("DEPRECATION")
fun <T: Serializable> Intent.intentSerializable(key: String, model: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, model)
    } else {
        this.getSerializableExtra(key) as T?
    }
}