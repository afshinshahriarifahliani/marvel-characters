package com.afshinshahriarifahliani.marvel_characters.data.repository

import com.afshinshahriarifahliani.marvel_characters.data.model.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.repository.datasource.RemoteDatasource
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import com.afshinshahriarifahliani.marvel_characters.domain.repository.MarvelRepository
import retrofit2.Response

class MarvelRepositoryImpl(private val remoteDatasource: RemoteDatasource):MarvelRepository {
    override suspend fun getAllCharacters(offset: Int?): Resource<MarvelApiResponse> {
        return convertResponseToResource(remoteDatasource.getAllCharacters(offset))
    }

    override suspend fun getSingleCharacterById(characterId: Int): Resource<MarvelApiResponse> {
        return convertResponseToResource(remoteDatasource.getSingleCharacterById(characterId))
    }

    override suspend fun saveFavoriteCharacter() {
        TODO("Not yet implemented")
    }

    private fun convertResponseToResource(response:Response<MarvelApiResponse>): Resource<MarvelApiResponse> {
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}