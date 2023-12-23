package com.example.filmku.di

import com.example.filmku.ui.screen.main.MainViewModel
import kotlinx.coroutines.MainScope
import com.example.filmku.repository.MainRepository
import org.koin.dsl.module

val appModules = module {
    single { MainScope() }
    MainViewModel(MainRepository(get())) }
    // Add more dependencies as needed


