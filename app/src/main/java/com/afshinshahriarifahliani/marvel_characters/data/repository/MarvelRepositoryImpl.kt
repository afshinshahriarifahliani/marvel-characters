package com.afshinshahriarifahliani.marvel_characters.data.repository

import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.data.model.comics.ComicResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.events.EventsResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.series.SeriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.stories.StoriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.LocalDatasource
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.RemoteDatasource
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MarvelRepositoryImpl(private val remoteDatasource: RemoteDatasource , private val localDatasource: LocalDatasource):MarvelRepository {
    override suspend fun getAllCharacters(offset: Int?): Resource<MarvelApiResponse> {
        return convertResponseToResource(remoteDatasource.getAllCharacters(offset))
    }

    override suspend fun getSingleCharacterById(characterId: Int): Resource<MarvelApiResponse> {
        return convertResponseToResource(remoteDatasource.getSingleCharacterById(characterId))
    }

    override suspend fun getCharacterComics(characterId: Int): Resource<ComicResponse> {
      return convertResponseToResource(remoteDatasource.getCharacterComics(characterId))
    }

    override suspend fun getCharacterSeries(characterId: Int): Resource<SeriesResponse> {
        return convertResponseToResource(remoteDatasource.getCharacterSeries(characterId))
    }

    override suspend fun getCharacterEvents(characterId: Int): Resource<EventsResponse> {
      return convertResponseToResource(remoteDatasource.getCharacterEvents(characterId))
    }

    override suspend fun getCharacterStories(characterId: Int): Resource<StoriesResponse> {
       return convertResponseToResource(remoteDatasource.getCharacterStories(characterId))
    }

    override suspend fun saveFavoriteCharacter() {
        TODO("Not yet implemented")
    }

    override suspend fun saveAllCharacters(characters: List<MarvelCharacter>) {
      return localDatasource.saveAllCharacters(characters)
    }

    override fun getAllSavedCharacters(): Flow<List<MarvelCharacter>> {
       return localDatasource.getAllSavedCharacters()
    }

    private fun <T> convertResponseToResource(response:Response<T>): Resource<T> {
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}