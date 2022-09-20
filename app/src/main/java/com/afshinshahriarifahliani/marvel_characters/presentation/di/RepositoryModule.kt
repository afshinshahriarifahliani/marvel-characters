package com.afshinshahriarifahliani.marvel_characters.presentation.di

import com.afshinshahriarifahliani.marvel_characters.data.repository.MarvelRepositoryImpl
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.RemoteDatasource
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMarvelRepository(remoteDatasource: RemoteDatasource): MarvelRepository {
        return MarvelRepositoryImpl(remoteDatasource)
    }
}