package com.example.koinstateloss

import androidx.lifecycle.ViewModel

class FirstFragmentViewModel(
    initialState: String,
    val sharedViewModel: SharedViewModel
) : ViewModel() {
    init {
        sharedViewModel.state = initialState
    }

}