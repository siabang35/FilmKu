package com.example.filmku.data.model.response

import com.squareup.moshi.Json
import com.example.filmku.data.model.Genre

class GenreResponse(
    @Json(name = "genres")
    var genres: List<Genre>
)