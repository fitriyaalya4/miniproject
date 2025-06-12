package com.mobproassesment.miniproject3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobproassesment.miniproject3.model.Tanaman
import com.mobproassesment.miniproject3.network.TanamanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Tanaman>())
            private set
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = TanamanApi.service.getTanaman()
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}