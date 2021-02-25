package com.coroutineslogapp.ui.util

import android.app.AlertDialog
import android.content.Context
import com.coroutineslogapp.R

class SettingsDialog {
    var updateCheckedSortListener: (checkedOption: Int) -> Unit = {}
    var updateCheckedAffiliationListener: (checkedOption: Int, isChecked: Boolean) -> Unit =
        { _, _ -> }

    fun showSortDialog(context: Context, lastCheckedOption: Int) {
        val sortAlertDialog = AlertDialog.Builder(context)
        with(sortAlertDialog) {
            setTitle(context.getString(R.string.sort))
            val options = context.resources.getStringArray(R.array.sort)
            setSingleChoiceItems(
                options,
                lastCheckedOption
            ) { _, which ->
                updateCheckedSortListener(which)
            }
            setNegativeButton(context.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    fun showAffiliationOptionsDialog(context: Context, lastCheckedOptions: List<Int>) {
        val affiliationAlertDialog = AlertDialog.Builder(context)
        with(affiliationAlertDialog) {
            setTitle(context.resources.getString(R.string.affiliation))
            val affiliationOptions = context.resources.getStringArray(R.array.affiliation)
            val optionsState: BooleanArray =
                affiliationOptions.indices.map { lastCheckedOptions.contains(it) }.toBooleanArray()

            setMultiChoiceItems(
                affiliationOptions,
                optionsState,
            ) { _, which, isChecked ->
                updateCheckedAffiliationListener(which, isChecked)
            }
            setNegativeButton(context.resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }
}
