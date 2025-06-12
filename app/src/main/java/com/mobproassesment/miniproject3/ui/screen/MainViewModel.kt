package com.mobproassesment.miniproject3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobproassesment.miniproject3.model.Tanaman
import com.mobproassesment.miniproject3.network.TanamanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Tanaman>())
            private set
    var status = MutableStateFlow(TanamanApi.ApiStatus.LOADING)
            private set
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = TanamanApi.ApiStatus.LOADING
            try {
                data.value = TanamanApi.service.getTanaman()
                status.value = TanamanApi.ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}