package com.coroutineslogapp.domain.usecase

import com.coroutineslogapp.domain.repository.RepoListRepository
import javax.inject.Inject

class SettingsUseCase @Inject constructor(private val repoListRepository: RepoListRepository) {

    fun saveSettings(affiliationIndices: List<Int>, sortTypeInd: Int) {
        repoListRepository.saveSettings(affiliationIndices, sortTypeInd)
    }

    fun getStoredAffiliationIndices() = repoListRepository.getStoredAffiliationIndices()

    fun getStoredSortType() = repoListRepository.getStoredSortTypeIndices()
}
