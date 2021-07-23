package com.example.platzi_challenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.platzi_challenge.data.Beer
import com.example.platzi_challenge.network.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val repository: BeerRepository
): BaseViewModel() {
    private val _beers = MutableLiveData<List<Beer>>()
    val beers: LiveData<List<Beer>> get() = _beers

    private var nextPage = 1

    fun fetchBeer() {
        if (mRequestStatus.value !is RequestStatus.Loading) {
            addRequest(::fetchCurrentPage, ::handleBeerResponse)
        }
    }

    private suspend fun fetchCurrentPage() = repository.getBeers(nextPage)

    private fun handleBeerResponse(beers: List<Beer>) {
        nextPage++
        _beers.postValue(_beers.value?.let { it + beers } ?: beers)
    }
}