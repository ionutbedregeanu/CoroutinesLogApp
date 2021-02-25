package com.coroutineslogapp.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutineslogapp.domain.usecase.GetUserUseCase
import com.coroutineslogapp.domain.usecase.LogoutUseCase
import com.coroutineslogapp.ui.mappers.mapToUIModel
import com.coroutineslogapp.ui.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val userLiveData = MutableLiveData<User?>()
    val loaderLiveData = MutableLiveData<Boolean>()

    fun getCurrentUserDetails(shouldRefresh: Boolean) {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            getUserUseCase.getUser(shouldRefresh)
                .onStart { loaderLiveData.postValue(true) }
                .onCompletion { loaderLiveData.postValue(false) }
                .collect { user ->
                    userLiveData.postValue(user?.mapToUIModel())
                }
        }
    }

    fun logoutUser() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            logoutUseCase()
        }
    }
}
