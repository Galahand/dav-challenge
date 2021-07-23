package com.example.platzi_challenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzi_challenge.data.Response
import kotlinx.coroutines.launch

sealed class RequestStatus {
    object Idle : RequestStatus()
    object Loading : RequestStatus()
    class Error(val throwable: Throwable) : RequestStatus()
}

open class BaseViewModel : ViewModel() {
    protected val mRequestStatus = MutableLiveData<RequestStatus>(RequestStatus.Idle)
    val requestStatus: LiveData<RequestStatus> get() = mRequestStatus

    protected inline fun <T> addRequest(
        noinline operation: suspend () -> Response<T>,
        crossinline successAction: (T) -> Unit
    )  {
        mRequestStatus.postValue(RequestStatus.Loading)
        viewModelScope.launch {
            when (val result = operation.invoke()) {
                is Response.Success -> {
                    mRequestStatus.postValue(RequestStatus.Idle)
                    successAction.invoke(result.data)
                }
                is Response.Error ->  {
                    println(result.throwable)
                    mRequestStatus.postValue(RequestStatus.Error(result.throwable))
                }
            }
        }
    }
}