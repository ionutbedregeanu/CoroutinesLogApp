package com.coroutineslogapp.ui.util

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, messageId: Int, colorId: Int) {
    val message = view.resources.getString(messageId)
    val snackbar = Snackbar
        .make(view, message, Snackbar.LENGTH_LONG)
    snackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, colorId))
    snackbar.setTextColor(Color.WHITE)
    snackbar.show()
}
