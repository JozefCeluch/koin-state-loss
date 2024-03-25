package com.example.koinstateloss

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SharedViewModel(application = androidApplication(), savedStateHandle = get()) }
    viewModel { (shared: SharedViewModel, initialState: String) ->
        FirstFragmentViewModel(
            sharedViewModel = shared,
            initialState = initialState
        )
    }
    viewModel { (shared: SharedViewModel) -> SecondFragmentViewModel(sharedViewModel = shared) }
}