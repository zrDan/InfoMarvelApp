package com.gtdan.marvelucm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtdan.marvelucm.ApiStatus
import com.gtdan.marvelucm.network.MarvelApi
import com.gtdan.marvelucm.network.model.ItemModel
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    private val _series = MutableLiveData<List<ItemModel>>()
    val series: LiveData<List<ItemModel>> = _series
    val status: LiveData<ApiStatus> = _status

    init {
        getSeries()
    }

    private fun getSeries() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val listSerieResult = MarvelApi.retrofitService.getSeries()
                _series.value = listSerieResult.items
                _status.value = ApiStatus.DONE

            } catch (e: Exception) {
                _series.value = listOf()
                _status.value = ApiStatus.ERROR
            }
        }
    }
}