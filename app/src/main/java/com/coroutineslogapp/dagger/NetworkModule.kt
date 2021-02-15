package com.coroutineslogapp.dagger

import android.app.Application
import com.coroutineslogapp.ui.util.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object NetworkModule {

    @Provides
    fun provideNetworkUtil(application: Application) = NetworkUtil(application)
}
