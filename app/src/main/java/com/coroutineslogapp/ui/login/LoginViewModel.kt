package com.coroutineslogapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutineslogapp.domain.usecase.NavigationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val navigationUseCase: NavigationUseCase) : ViewModel() {
    val shouldNavigateToNextScreen = MutableLiveData<Boolean>()

    fun loginUser(username: String, accessToken: String) {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            shouldNavigateToNextScreen.postValue(navigationUseCase.shouldNavigateToNextScreen(username, accessToken))
        }
    }
}
