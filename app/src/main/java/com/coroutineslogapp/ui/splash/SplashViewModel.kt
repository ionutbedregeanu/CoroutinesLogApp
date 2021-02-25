package com.coroutineslogapp.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coroutineslogapp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    val navigationDirection = MutableLiveData<SplashDirections>()

    init {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            if (userRepository.getUserFromDatabase() == null) {
                navigationDirection.postValue(SplashDirections.Login)
            } else {
                navigationDirection.postValue(SplashDirections.Profile)
            }
        }
    }
}
