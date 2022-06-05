package com.task.foodrecipe.di

import com.task.foodrecipe.BuildConfig
import com.task.foodrecipe.data.remote.baseclient.RetroNetwork
import com.task.foodrecipe.data.remote.baseclient.interceptor.KeyInterceptor
import com.task.foodrecipe.data.remote.microservices.foodrepos.FoodRetroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesKeyInterceptor(): KeyInterceptor =
        KeyInterceptor(
            apiKey = BuildConfig.API_KEY,
            apiHost = BuildConfig.API_HOST
        )

    @Provides
    fun providesGitRepoRetroService(keyInterceptor: KeyInterceptor): FoodRetroService =
        RetroNetwork(keyInterceptor = keyInterceptor).createService(FoodRetroService::class.java)
}