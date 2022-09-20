package com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.afshinshahriarifahliani.marvel_characters.data.model.MarvelApiResponse
import com.afshinshahriarifahliani.marvel_characters.util.Resource
import com.afshinshahriarifahliani.marvel_characters.util.isNetworkAvailable
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.GetAllCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MarvelViewModel(
    private val app: Application,
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : AndroidViewModel(app) {


    val characters: MutableLiveData<Resource<MarvelApiResponse>> = MutableLiveData()

    fun getAllCharacters(offset: Int?) = viewModelScope.launch(Dispatchers.IO) {
        characters.postValue(Resource.Loading())
        try{
            if(isNetworkAvailable(app)) {

                val apiResult = getAllCharactersUseCase.execute(offset)
                characters.postValue(apiResult)
            }else{
                characters.postValue(Resource.Error("Network is not available"))
            }

        }catch (e: Exception){
            characters.postValue(Resource.Error(e.message.toString()))
        }

    }

}