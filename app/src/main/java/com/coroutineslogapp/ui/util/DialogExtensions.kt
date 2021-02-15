package com.coroutineslogapp.ui.util

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.coroutineslogapp.R

fun Fragment.showErrorDialog(errorMessage: String): AlertDialog = AlertDialog.Builder(requireContext())
    .setTitle(R.string.error_title)
    .setPositiveButton(R.string.ok) { dialog, _ ->
        dialog.dismiss()
    }
    .setMessage(errorMessage)
    .show()
