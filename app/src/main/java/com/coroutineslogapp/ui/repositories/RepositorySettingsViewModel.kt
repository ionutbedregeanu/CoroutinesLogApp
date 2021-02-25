package com.coroutineslogapp.ui.repositories

import androidx.lifecycle.ViewModel
import com.coroutineslogapp.domain.usecase.SettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositorySettingsViewModel @Inject constructor(private val settingsUseCase: SettingsUseCase) :
    ViewModel() {
    var currentAffiliationIndices = ArrayList<Int>()
    var currentSortTypeIndex = 0

    fun getLastCheckedAffiliation(): List<Int> {
        if (currentAffiliationIndices.isEmpty()) {
            currentAffiliationIndices.addAll(settingsUseCase.getStoredAffiliationIndices())
        }
        return currentAffiliationIndices
    }

    fun getLastCheckedSort(): Int {
        if (currentSortTypeIndex == 0) {
            currentSortTypeIndex = settingsUseCase.getStoredSortType()
        }
        return currentSortTypeIndex
    }

    fun saveSettings() {
        settingsUseCase.saveSettings(currentAffiliationIndices, currentSortTypeIndex)
    }

    fun updateCurrentAffiliationIndices(checkedOption: Int, checked: Boolean) {
        if (checked) {
            currentAffiliationIndices.add(checkedOption)
        } else {
            currentAffiliationIndices.remove(checkedOption)
        }
    }
}
