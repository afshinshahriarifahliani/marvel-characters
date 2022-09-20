package com.afshinshahriarifahliani.marvel_characters.presentation.di

import android.app.Application
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.GetAllCharactersUseCase
import com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel.MarvelViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideMarvelViewModelFactory(
        app: Application,
        getAllCharactersUseCase: GetAllCharactersUseCase
    ): MarvelViewModelFactory {
        return MarvelViewModelFactory(app, getAllCharactersUseCase)
    }
}