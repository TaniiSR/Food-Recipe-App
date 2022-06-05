package com.task.foodrecipe.di

import com.task.foodrecipe.data.remote.microservices.foodrepos.FoodRepoApi
import com.task.foodrecipe.data.remote.microservices.foodrepos.FoodRepositoryRemote
import com.task.foodrecipe.domain.DataRepository
import com.task.foodrecipe.domain.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideFoodRemoteRepository(foodRepositoryRemote: FoodRepositoryRemote): FoodRepoApi

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): IDataRepository
}