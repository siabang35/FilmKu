package com.example.filmku.repository

import com.example.filmku.data.model.response.MovieResponse
import com.example.filmku.data.model.response.TrendingResponse
import com.example.filmku.network.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    /**
     * Fetches the trending movies every week.
     *
     * @param apiKey The API key for authentication.
     * @return A [Flow] emitting [Resource] with [TrendingResponse].
     */
    suspend fun getTrendingMovieEveryWeek(apiKey: String?): Flow<Resource<TrendingResponse>>

    /**
     * Fetches the latest released movies.
     *
     * @param apiKey The API key for authentication.
     * @param language The language of the movies.
     * @return A [Flow] emitting [Resource] with [MovieResponse].
     */
    suspend fun getLatestMovieReleased(apiKey: String?, language: String): Flow<Resource<MovieResponse>>

    /**
     * Fetches action movies based on genre.
     *
     * @param apiKey The API key for authentication.
     * @param genreID The ID of the action genre.
     * @param page The page number of the results.
     * @return A [Flow] emitting [Resource] with [MovieResponse].
     */
    suspend fun getMovieAction(apiKey: String?, genreID: String?, page: String?): Flow<Resource<MovieResponse>>
}
