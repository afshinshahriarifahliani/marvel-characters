package com.afshinshahriarifahliani.marvel_characters.presentation.di

import com.afshinshahriarifahliani.marvel_characters.data.api.MarvelApiService
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.RemoteDatasource
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasourceImpl.RemoteDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(marvelApiService: MarvelApiService): RemoteDatasource {
        return RemoteDatasourceImpl(marvelApiService)
    }
}