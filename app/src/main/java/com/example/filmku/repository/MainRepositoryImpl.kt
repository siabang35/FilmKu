package com.example.filmku.repository

import com.example.filmku.data.model.response.MovieResponse
import com.example.filmku.data.model.response.TrendingResponse
import com.example.filmku.network.ApiService
import com.example.filmku.network.Resource
import com.example.filmku.network.request
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {

    override suspend fun getTrendingMovieEveryWeek(
        apiKey: String?
    ): Flow<Resource<TrendingResponse>> = flow {
        request { apiService.getTrendingMovieEveryWeek(apiKey) }
            .collect { result ->
                emit(result)
            }
    }

    override suspend fun getLatestMovieReleased(
        apiKey: String?,
        language: String
    ): Flow<Resource<MovieResponse>> = flow {
        request { apiService.getLatestMovieReleased(apiKey, language) }
            .collect { result ->
                emit(result)
            }
    }


    override suspend fun getMovieAction(
        apiKey: String?,
        genreID: String?,
        page: String?
    ): Flow<Resource<MovieResponse>> = flow {
        request { apiService.getMovieByGenre(apiKey, genreID, page) }
            .collect { result ->
                emit(result)
            }
    }
}
