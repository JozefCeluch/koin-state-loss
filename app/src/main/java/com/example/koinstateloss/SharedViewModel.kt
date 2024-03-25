package com.example.koinstateloss

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SharedViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val application: Application
) : ViewModel() {

    init {
        Log.e("SharedViewModel", "SHARED VM EXISTING KEYS: ${savedStateHandle.keys()}")
    }

    private var currentState: String? = null
        get() = savedStateHandle["state_key"]
        set(value) {
            field = value
            savedStateHandle["state_key"] = value
        }

    var state: String = "NO STATE"
        get() = currentState ?: "NO STATE"
        set(value) {
            field = value
            currentState = value
        }

    override fun onCleared() {
        super.onCleared()
        Toast.makeText(application, "SHARED VM CLEARED", Toast.LENGTH_SHORT).show()
        Log.e("SharedViewModel", "SHARED VM CLEARED")
    }
}