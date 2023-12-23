package com.example.filmku.ui.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmku.data.model.MovieDetails
import com.example.filmku.data.model.response.TrailerResponse
import com.example.filmku.network.Resource
import com.example.filmku.repository.DetailMovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DetailMovieRepository
) : ViewModel() {

    fun getMovieDetailByID(movieID: String, apiKey: String): LiveData<Resource<MovieDetails>> {
        val response = MutableLiveData<Resource<MovieDetails>>()
        viewModelScope.launch {
            repository.getMovieDetail(movieID, apiKey).collect {
                response.value = it
            }
        }
        return response
    }

    fun getMovieTrailerByID(movieID: String, apiKey: String): LiveData<Resource<TrailerResponse>> {
        val response = MutableLiveData<Resource<TrailerResponse>>()
        viewModelScope.launch {
            repository.getMovieTrailer(movieID, apiKey).collect {
                response.value = it
            }
        }
        return response
    }
}
