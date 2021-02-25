package com.coroutineslogapp.ui.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutineslogapp.domain.model.TypeOfRepositoryList
import com.coroutineslogapp.domain.usecase.GetRepositoriesUseCase
import com.coroutineslogapp.ui.model.RepositoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {
    val repositoriesLiveData = MutableLiveData<List<RepositoryItem>?>()

    fun loadUserRepositories(typeOfRepositoryList: TypeOfRepositoryList) {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            repositoriesLiveData.postValue(
                getRepositoriesUseCase(typeOfRepositoryList = typeOfRepositoryList)
            )
        }
    }
}
