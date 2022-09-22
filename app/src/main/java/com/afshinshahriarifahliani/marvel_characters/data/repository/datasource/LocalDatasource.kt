package com.afshinshahriarifahliani.marvel_characters.data.repository.datasource

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afshinshahriarifahliani.marvel_characters.data.db.MarvelDao
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface LocalDatasource {
    suspend fun saveAllCharacters(characters:List<MarvelCharacter>)
    fun getAllSavedCharacters(): Flow<List<MarvelCharacter>>
    suspend fun saveFavoriteCharacter(character: MarvelCharacter)
    suspend fun deleteSavedCharacter(character: MarvelCharacter)
}