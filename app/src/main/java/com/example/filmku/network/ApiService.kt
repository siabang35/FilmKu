package com.example.filmku.network

import com.example.filmku.data.model.MovieDetails
import com.example.filmku.data.model.response.GenreResponse
import com.example.filmku.data.model.response.MovieResponse
import com.example.filmku.data.model.response.TrailerResponse
import com.example.filmku.data.model.response.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
    ): Response<GenreResponse>

    @GET("/3/trending/movie/week")
    suspend fun getTrendingMovieEveryWeek(
        @Query("api_key") apiKey: String?
    ): Response<TrendingResponse>

    @GET("/3/movie/upcoming")
    suspend fun getLatestMovieReleased(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("sort_by") shortBy: String = "primary_release_date.desc"
    ): Response<MovieResponse>

    @GET("/3/discover/movie")
    suspend fun getMovieByGenre(
        @Query("api_key") apiKey: String?,
        @Query("with_genres") genreID: String?,
        @Query("page") page: String?,
        // @Query("sort_by") shortBy: String = Constant.PRIMARY_RELEASE_DATE_DESC
    ): Response<MovieResponse>

    @GET("/3/movie/{movieID}")
    suspend fun getMovieDetailsById(
        @Path("movieID") id: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetails>

    @GET("/3/movie/{movieID}/videos")
    suspend fun getMovieTrailerById(
        @Path("movieID") movieID: String?,
        @Query("api_key") apiKey: String?,
    ): Response<TrailerResponse>
}
