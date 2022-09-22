package com.afshinshahriarifahliani.marvel_characters.presentation.di

import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.CharacterAdapter
import com.afshinshahriarifahliani.marvel_characters.presentation.adapter.ItemDetailsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideCharacterAdapter(): CharacterAdapter {
        return CharacterAdapter()
    }
    @Singleton
    @Provides
    fun provideItemDetailsAdapter(): ItemDetailsAdapter {
        return ItemDetailsAdapter()
    }

}