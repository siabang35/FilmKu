package com.example.filmku.repository

import com.example.filmku.data.model.MovieDetails
import com.example.filmku.data.model.response.TrailerResponse
import com.example.filmku.network.ApiService
import com.example.filmku.network.Resource
import com.example.filmku.network.request
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailMovieRepositoryImpl(
    private val apiService: ApiService
): DetailMovieRepository {
    override suspend fun getMovieDetail(
        movieID: String,
        apiKey: String
    ): Flow<Resource<MovieDetails>> = flow {
        request { apiService.getMovieDetailsById(movieID, apiKey) }
            .collect { result ->
                emit(result)
            }
    }

    override suspend fun getMovieTrailer(
        movieID: String,
        apiKey: String
    ): Flow<Resource<TrailerResponse>> = flow {
        request { apiService.getMovieTrailerById(movieID, apiKey) }
            .collect { result ->
                emit(result)
            }
    }
}
