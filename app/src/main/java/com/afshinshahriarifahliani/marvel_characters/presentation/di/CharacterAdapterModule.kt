package com.afshinshahriarifahliani.marvel_characters.presentation.di

import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.CharacterAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterAdapterModule {

    @Singleton
    @Provides
    fun provideCharacterAdapter(): CharacterAdapter {
        return CharacterAdapter()
    }
}