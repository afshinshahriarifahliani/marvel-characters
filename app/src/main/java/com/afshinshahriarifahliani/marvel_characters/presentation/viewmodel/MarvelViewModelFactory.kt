package com.afshinshahriarifahliani.marvel_characters.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afshinshahriarifahliani.marvel_characters.domain.usecase.GetAllCharactersUseCase
import java.lang.IllegalArgumentException

class MarvelViewModelFactory(private val app: Application,
                             private val getAllCharactersUseCase: GetAllCharactersUseCase
):ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MarvelViewModel::class.java)){
            return MarvelViewModel(app,getAllCharactersUseCase) as T
          }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}