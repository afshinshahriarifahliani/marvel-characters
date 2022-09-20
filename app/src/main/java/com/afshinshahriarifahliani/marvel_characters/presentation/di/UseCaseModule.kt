package com.afshinshahriarifahliani.marvel_characters.presentation.di

import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllCharactersUseCase(repository: MarvelRepository): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(repository)
    }
}