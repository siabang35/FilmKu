package com.example.filmku.di

import com.example.filmku.ui.screen.main.MainViewModel
import com.example.filmku.ui.screen.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    // ViewModel declarations
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
