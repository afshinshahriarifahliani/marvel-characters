package com.afshinshahriarifahliani.marvel_characters.data.repository.datasourceImpl

import com.afshinshahriarifahliani.marvel_characters.data.db.MarvelDao
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.LocalDatasource
import kotlinx.coroutines.flow.Flow

class LocalDatasourceImpl(private val marvelDao: MarvelDao) : LocalDatasource {
    override suspend fun saveAllCharacters(characters: List<MarvelCharacter>) {
         marvelDao.insertAllCharacters(characters)
    }

    override fun getAllSavedCharacters(): Flow<List<MarvelCharacter>> {
      return marvelDao.getAllSavedCharacters()
    }

    override suspend fun saveFavoriteCharacter(character: MarvelCharacter) {
        marvelDao.saveFavoriteCharacter(character)
    }

    override suspend fun deleteSavedCharacter(character: MarvelCharacter) {
        marvelDao.deleteSavedCharacter(character)
    }
}