package com.example.filmku.data.model.response

import com.squareup.moshi.Json
import com.example.filmku.data.model.Trailer

data class TrailerResponse(
    @Json(name ="id")
    val id: Long,

    @Json(name ="results")
    val results: List<Trailer>,
)
