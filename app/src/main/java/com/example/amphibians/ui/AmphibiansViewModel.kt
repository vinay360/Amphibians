package com.example.amphibians.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch

enum class Status {
    LOADING,
    ERROR,
    DONE
}

class AmphibiansViewModel : ViewModel() {
    private var _amphibians = MutableLiveData<List<Amphibian>>()
    private var _status = MutableLiveData<Status>()

    val amphibians : LiveData<List<Amphibian>>
        get() = _amphibians
    val status : LiveData<Status>
        get() = _status

    init {
        getAmphibiansList()
    }

    private fun getAmphibiansList() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                _amphibians.value = AmphibiansApi.retrofitService.getList()
                _status.value = Status.DONE
               // Log.d("TAG", status.value.toString())
            } catch (e: Exception) {
                _status.value = Status.ERROR
            }
        }
    }
}