package com.coroutineslogapp.ui.repositories

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.coroutineslogapp.R
import com.coroutineslogapp.ui.util.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repository_settings.*
import kotlinx.android.synthetic.main.fragment_repository_settings.affiliation_item as affiliationItem
import kotlinx.android.synthetic.main.fragment_repository_settings.sort_item as sortItem

@AndroidEntryPoint
class RepositorySettingsFragment : Fragment() {
    private val viewModel: RepositorySettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        affiliationItem.setOnClickListener {
            val affiliationDialog = SettingsDialog()
            affiliationDialog.showAffiliationOptionsDialog(requireContext(), viewModel.getLastCheckedAffiliation())
            affiliationDialog.updateCheckedAffiliationListener = { checkedOption, isChecked ->
                viewModel.updateCurrentAffiliationIndices(checkedOption, isChecked)
            }
        }

        sortItem.setOnClickListener {
            val sortDialog = SettingsDialog()
            sortDialog.showSortDialog(requireContext(), viewModel.getLastCheckedSort())
            sortDialog.updateCheckedSortListener = { sortTypeId ->
                viewModel.currentSortTypeIndex = sortTypeId
            }
        }

        toolbar.setNavigationOnClickListener {
            viewModel.saveSettings()
            findNavController().popBackStack()
        }
    }
}
