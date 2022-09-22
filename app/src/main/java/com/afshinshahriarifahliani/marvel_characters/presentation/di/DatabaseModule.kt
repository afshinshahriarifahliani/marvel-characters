package com.afshinshahriarifahliani.marvel_characters.presentation.di

import android.app.Application
import androidx.room.Room
import com.afshinshahriarifahliani.marvel_characters.data.db.MarvelDao
import com.afshinshahriarifahliani.marvel_characters.data.db.MarvelDataBase
import com.afshinshahriarifahliani.marvel_characters.util.DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMarvelDatabase(app:Application):MarvelDataBase {
        return Room.databaseBuilder(
            app.applicationContext,
            MarvelDataBase::class.java,
            DATABASE
        ).build()

    }

    @Singleton
    @Provides
    fun provideNewsDao(marvelDatabase: MarvelDataBase): MarvelDao {
        return marvelDatabase.getMarvelDAO()
    }

}