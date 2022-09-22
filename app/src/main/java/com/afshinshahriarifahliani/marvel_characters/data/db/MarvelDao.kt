package com.afshinshahriarifahliani.marvel_characters.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters:List<MarvelCharacter>)

    @Query("SELECT * FROM characters_table")
    fun getAllSavedCharacters(): Flow<List<MarvelCharacter>>
}