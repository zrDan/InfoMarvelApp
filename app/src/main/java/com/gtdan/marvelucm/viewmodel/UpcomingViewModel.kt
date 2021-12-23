package com.gtdan.marvelucm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtdan.marvelucm.ApiStatus
import com.gtdan.marvelucm.network.MarvelApi
import com.gtdan.marvelucm.network.model.ItemUpcomingModel
import kotlinx.coroutines.launch

class UpcomingViewModel: ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    private val _upcoming = MutableLiveData<List<ItemUpcomingModel>>()
    val upcoming: LiveData<List<ItemUpcomingModel>> = _upcoming
    val status: LiveData<ApiStatus> = _status

    init {
        getUpcoming()
    }

    private fun getUpcoming() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val listUpcomingResult = MarvelApi.retrofitService.getUpcoming()
                _upcoming.value = listUpcomingResult.items
                _status.value = ApiStatus.DONE

            } catch (e: Exception) {
                _upcoming.value = listOf()
                _status.value = ApiStatus.ERROR
            }
        }
    }
}