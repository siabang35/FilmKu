package com.example.filmku.ui.screen.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.example.filmku.BuildConfig
import com.example.filmku.data.model.MovieDetails
import com.example.filmku.data.model.response.TrailerResponse
import com.example.filmku.databinding.DialogMovieDetailBinding
import com.example.filmku.network.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogDetail : DialogFragment() {

    private var movieID: Int? = null

    private lateinit var binding: DialogMovieDetailBinding
    private val viewModel by viewModel<DetailViewModel>()

    companion object {
        const val TAG = "SimpleDialog"
        private const val KEY_MOVIE_ID = "MOVIE_ID"

        fun newInstance(bundle: Bundle): DialogDetail {
            return DialogDetail().apply {
                arguments = Bundle().apply {
                    putInt(KEY_MOVIE_ID, bundle.getInt(KEY_MOVIE_ID))
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieID = arguments?.getInt(KEY_MOVIE_ID)
        subscribeUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        binding = DialogMovieDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btClose.setOnClickListener {
            dismiss()
        }

        return view
    }

    private fun subscribeUI() {
        viewModel.getMovieDetailByID(movieID.toString(), BuildConfig.API_KEY).observe(this) { result ->
            when (result.state) {
                ResourceState.LOADING -> Log.d(TAG, "onLoading")
                ResourceState.SUCCESS -> result.data?.let { updatePrimaryInformationUI(it) }
                ResourceState.ERROR -> Log.d(TAG, "onError")
            }
        }

        viewModel.getMovieTrailerByID(movieID.toString(), BuildConfig.API_KEY).observe(this) { result ->
            when (result.state) {
                ResourceState.LOADING -> Log.d(TAG, "onLoading")
                ResourceState.SUCCESS -> result.data?.let { updateMovieTrailer(it) }
                ResourceState.ERROR -> Log.d(TAG, "onError")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updatePrimaryInformationUI(movieDetails: MovieDetails) {
        binding.tvMovieTitle.text = movieDetails.title

        // Check if voteAverage is not null before performing operations
        movieDetails.voteAverage?.let { voteAverage ->
            val rating = 5 * (voteAverage / 10f)
            binding.rbMovie.rating = rating
        }

        // Check if voteCount is not null before using it in the string interpolation
        binding.tvVoteAverage.text = "${movieDetails.voteCount ?: 0} votes"
        binding.tvMovieDesc.text = movieDetails.overview
    }

    private fun updateMovieTrailer(trailerResponse: TrailerResponse) {
        var key = ""
        for (j in trailerResponse.results) {
            key = j.key.toString()
            println("Key : $key")
        }
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(key, 0f)
            }
        })
    }
}
