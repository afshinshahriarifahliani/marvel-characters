package com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.characters.MarvelCharacter
import com.afshinshahriarifahliani.marvel_characters.data.model.comics.ComicResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.events.EventsResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.series.SeriesResponse
import com.afshinshahriarifahliani.marvel_characters.data.model.stories.StoriesResponse
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.*
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import com.afshinshahriarifahliani.marvel_characters.util.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarvelViewModel(
    private val app: Application,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getSingleCharacterByIdUseCase: GetSingleCharacterByIdUseCase,
    private val getCharacterComicsUseCase: GetCharacterComicsUseCase,
    private val getCharacterSeriesUseCase: GetCharacterSeriesUseCase,
    private val getCharacterEventsUseCase: GetCharacterEventsUseCase,
    private val getCharacterStoriesUseCase: GetCharacterStoriesUseCase,
    private val searchCharacterNameToStartWithUseCase: SearchCharacterNameToStartWithUseCase,
    private val saveFavoriteCharacterUseCase: SaveFavoriteCharacterUseCase,
    private val getAllSavedCharactersUseCase:GetAllSavedCharactersUseCase,
    private val deleteSavedCharacterUseCase:DeleteSavedCharacterUseCase
) : AndroidViewModel(app) {


    private val _characters: MutableLiveData<Resource<MarvelApiResponse>> = MutableLiveData()
    val characters: LiveData<Resource<MarvelApiResponse>> = _characters
    private val _singleCharacter: MutableLiveData<Resource<MarvelApiResponse>> = MutableLiveData()
    val singleCharacter: LiveData<Resource<MarvelApiResponse>> = _singleCharacter
    private val _comics: MutableLiveData<Resource<ComicResponse>> = MutableLiveData()
    val comics: LiveData<Resource<ComicResponse>> = _comics
    private val _series: MutableLiveData<Resource<SeriesResponse>> = MutableLiveData()
    val series: LiveData<Resource<SeriesResponse>> = _series
    private val _events: MutableLiveData<Resource<EventsResponse>> = MutableLiveData()
    val events: LiveData<Resource<EventsResponse>> = _events
    private val _stories: MutableLiveData<Resource<StoriesResponse>> = MutableLiveData()
    val stories: LiveData<Resource<StoriesResponse>> = _stories
    private val _characterSearchResult: MutableLiveData<Resource<MarvelApiResponse>> =
        MutableLiveData()
    val characterSearchResult: LiveData<Resource<MarvelApiResponse>> = _characterSearchResult

    fun getAllSavedCharacters() = liveData{
        getAllSavedCharactersUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteArticle(character: MarvelCharacter) = viewModelScope.launch {
        deleteSavedCharacterUseCase.execute(character)
    }


    fun searchCharacterNameToStartWithUseCase(query: String, offset: Int?) =
        viewModelScope.launch(Dispatchers.IO) {
            _characterSearchResult.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {

                    val apiResult = searchCharacterNameToStartWithUseCase.execute(query, offset)
                    _characterSearchResult.postValue(apiResult)
                } else {
                    _characterSearchResult.postValue(Resource.Error("Network is not available"))
                }

            } catch (e: Exception) {
                _characterSearchResult.postValue(Resource.Error(e.message.toString()))
            }

        }

    fun getCharacterSeries(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _series.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getCharacterSeriesUseCase.execute(characterId)
                _series.postValue(apiResult)
            } else {
                _series.postValue(Resource.Error("Network is not available"))
            }

        } catch (e: Exception) {
            _series.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getCharacterComics(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _comics.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getCharacterComicsUseCase.execute(characterId)
                _comics.postValue(apiResult)
            } else {
                _comics.postValue(Resource.Error("Network is not available"))
            }

        } catch (e: Exception) {
            _comics.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getCharacterEvents(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _events.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getCharacterEventsUseCase.execute(characterId)
                _events.postValue(apiResult)
            } else {
                _events.postValue(Resource.Error("Network is not available"))
            }

        } catch (e: Exception) {
            _events.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getCharacterStories(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _stories.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getCharacterStoriesUseCase.execute(characterId)
                _stories.postValue(apiResult)
            } else {
                _stories.postValue(Resource.Error("Network is not available"))
            }

        } catch (e: Exception) {
            _stories.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getSingleCharacterById(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _singleCharacter.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getSingleCharacterByIdUseCase.execute(characterId)
                _singleCharacter.postValue(apiResult)
            } else {
                _singleCharacter.postValue(Resource.Error("Network is not available"))
            }

        } catch (e: Exception) {
            _singleCharacter.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun getAllCharacters(offset: Int?) = viewModelScope.launch(Dispatchers.IO) {
        _characters.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getAllCharactersUseCase.execute(offset)
                _characters.postValue(apiResult)
            } else {
                _characters.postValue(Resource.Error("Network is not available"))
            }

        } catch (e: Exception) {
            _characters.postValue(Resource.Error(e.message.toString()))
        }

    }

    fun addToFavorites(character: MarvelCharacter) = viewModelScope.launch(Dispatchers.IO) {
        saveFavoriteCharacterUseCase.execute(character)
    }

}