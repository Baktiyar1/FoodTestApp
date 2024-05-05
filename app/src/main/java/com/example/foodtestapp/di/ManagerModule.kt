package com.example.foodtestapp.di

import android.content.Context
import com.example.foodtestapp.ui.manager.navigation.NavigatorManager
import com.example.foodtestapp.ui.manager.navigation.NavigatorManagerImpl
import com.example.foodtestapp.ui.manager.toast.ShowToastUseCase
import com.example.foodtestapp.ui.manager.toast.ToastManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {

    @Provides
    @Singleton
    fun providesNavigatorManager(): NavigatorManager = NavigatorManagerImpl()

    @Provides
    @Singleton
    fun providesToastManager(@ApplicationContext context: Context): ShowToastUseCase =
        ToastManager(context = context)
}