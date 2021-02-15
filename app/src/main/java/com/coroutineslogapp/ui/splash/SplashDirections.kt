package com.coroutineslogapp.ui.splash

sealed class SplashDirections {
    object Login : SplashDirections()
    object Profile : SplashDirections()
}
