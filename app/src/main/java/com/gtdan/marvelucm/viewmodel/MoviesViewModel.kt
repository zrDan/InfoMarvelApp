package com.gtdan.marvelucm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtdan.marvelucm.ApiStatus
import com.gtdan.marvelucm.network.MarvelApi
import com.gtdan.marvelucm.network.model.ItemModel
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    private val _movies = MutableLiveData<List<ItemModel>>()
    val movies: LiveData<List<ItemModel>> = _movies
    val status: LiveData<ApiStatus> = _status

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val listMovieResult = MarvelApi.retrofitService.getMovies()
                _movies.value = listMovieResult.items
                _status.value = ApiStatus.DONE

            } catch (e: Exception) {
                _movies.value = listOf()
                _status.value = ApiStatus.ERROR
            }
        }
    }
}