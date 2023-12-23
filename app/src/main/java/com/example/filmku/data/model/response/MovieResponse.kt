package com.example.filmku.data.model.response

import com.squareup.moshi.Json
import com.example.filmku.data.model.Movie

class MovieResponse(
    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val results: List<Movie>,

    @Json(name = "total_pages")
    val totalPages: Int,

    @Json(name = "total_results")
    val totalResults: Int,
)
