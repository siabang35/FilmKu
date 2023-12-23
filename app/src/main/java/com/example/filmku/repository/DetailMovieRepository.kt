package com.example.filmku.repository

import com.example.filmku.data.model.MovieDetails
import com.example.filmku.data.model.response.TrailerResponse
import com.example.filmku.network.Resource
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {
    suspend fun getMovieDetail(movieID: String, apiKey: String): Flow<Resource<MovieDetails>>
    suspend fun getMovieTrailer(movieID: String, apiKey: String): Flow<Resource<TrailerResponse>>
}
