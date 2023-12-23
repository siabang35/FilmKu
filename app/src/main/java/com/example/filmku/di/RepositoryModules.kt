package com.example.filmku.di

import com.example.filmku.BuildConfig
import com.example.filmku.network.ApiService
import com.example.filmku.repository.MainRepositoryImpl
import com.example.filmku.repository.MainRepository
import com.example.filmku.repository.DetailMovieRepositoryImpl
import com.example.filmku.repository.DetailMovieRepository
import org.koin.dsl.module

val networkModule = module {
    // Add your network-related dependencies here, including ApiService
    single { createService<ApiService>(get(), BuildConfig.BASE_URL) }
}

val repositoryModules = module {
    // Repository declarations
    single<MainRepository> { MainRepositoryImpl(apiService = get()) }
    single<DetailMovieRepository> { DetailMovieRepositoryImpl(apiService = get()) }
}
