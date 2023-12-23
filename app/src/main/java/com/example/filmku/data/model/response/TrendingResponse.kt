package com.example.filmku.data.model.response

import com.squareup.moshi.Json
import com.example.filmku.data.model.Movie

data class TrendingResponse(
    @Json(name = "page")
    val page: Long,
    @Json(name = "results")
    val results: List<Movie>,
    @Json(name = "total_pages")
    val totalPages: Long,
    @Json(name = "total_results")
    val totalResults: Long
)
