package com.coroutineslogapp.ui.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutineslogapp.domain.usecase.RepositoryDetailsUseCase
import com.coroutineslogapp.ui.mappers.mapToRepositoryDetails
import com.coroutineslogapp.ui.model.RepositoryDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val repositoryDetailsUseCase: RepositoryDetailsUseCase
) : ViewModel() {
    val repositoryDetails = MutableLiveData<RepositoryDetails>()

    fun getRepositoryDetails(repositoryId: Long) {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            repositoryDetailsUseCase(repositoryId).collect { model ->
                repositoryDetails.postValue(model.mapToRepositoryDetails())
            }
        }
    }
}
