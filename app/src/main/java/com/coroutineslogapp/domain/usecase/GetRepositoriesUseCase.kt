package com.coroutineslogapp.domain.usecase

import com.coroutineslogapp.data.repository.DomainRepositoriesResponse
import com.coroutineslogapp.domain.model.SortType
import com.coroutineslogapp.domain.model.TypeOfRepositoryList
import com.coroutineslogapp.domain.model.getConcatenatedAffiliation
import com.coroutineslogapp.domain.repository.RepoListRepository
import com.coroutineslogapp.ui.mappers.mapToUIRepositories
import com.coroutineslogapp.ui.model.RepositoryItem
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repoListRepository: RepoListRepository) {

    suspend operator fun invoke(typeOfRepositoryList: TypeOfRepositoryList, ): List<RepositoryItem>? {
        val checkedAffiliation = getConcatenatedAffiliation(repoListRepository.getStoredAffiliationIndices())
        val checkedSortType = repoListRepository.getStoredSortTypeIndices()

        var mappedRepositories: List<RepositoryItem>? = null
        repoListRepository.getRepositories(typeOfRepositoryList, checkedAffiliation, SortType.values()[checkedSortType].value).collect { response ->
            mappedRepositories = when (response) {
                is DomainRepositoriesResponse.Success -> response.repositories.mapToUIRepositories()
                    .also { repoListRepository.saveRepositoriesLocally(response.repositories) }
                is DomainRepositoriesResponse.Error -> null
            }
        }
        return mappedRepositories
    }
}
