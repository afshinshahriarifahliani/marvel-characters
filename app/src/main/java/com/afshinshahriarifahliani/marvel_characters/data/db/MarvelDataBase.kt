package com.afshinshahriarifahliani.marvel_characters.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
@TypeConverters(Converters::class)
@Database(entities = [MarvelCharacter::class], version = 1, exportSchema = false)
abstract class MarvelDataBase : RoomDatabase() {
    abstract fun getMarvelDAO(): MarvelDao
}