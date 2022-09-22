package com.afshinshahriarifahliani.marvel_characters.data.db

import androidx.room.*
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters:List<MarvelCharacter>)

    @Query("SELECT * FROM characters_table")
    fun getAllSavedCharacters(): Flow<List<MarvelCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteCharacter(character: MarvelCharacter)

    @Delete
    suspend fun deleteSavedCharacter(character: MarvelCharacter)
}