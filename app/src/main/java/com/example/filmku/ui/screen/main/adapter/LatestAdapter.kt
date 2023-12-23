package com.example.filmku.ui.screen.main.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmku.BuildConfig
import com.example.filmku.R
import com.example.filmku.data.model.Movie
import com.example.filmku.databinding.DialogLoadingBinding
import com.example.filmku.databinding.GridItemLatestMovieBinding
import com.example.filmku.ui.screen.detail.DialogDetail
import com.example.filmku.util.viewBinding

class LatestAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private var data = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            MovieViewHolder(parent.viewBinding(GridItemLatestMovieBinding::inflate))
        } else {
            LoadingViewHolder(parent.viewBinding(DialogLoadingBinding::inflate))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_ITEM) {
            val item = data[position]
            if (item != null) {
                (holder as MovieViewHolder).bind(item)
            }
        }
    }

    inner class LoadingViewHolder(binding: DialogLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class MovieViewHolder(private val binding: GridItemLatestMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) = with(binding) {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + item.backdropPath)
                .error(R.mipmap.no_image)
                .into(ivMoviePoster)
            tvMovieTitle.text = item.title
            itemView.setOnClickListener {
                val args = Bundle()
                item.id?.let { it1 -> args.putInt("MOVIE_ID", it1) }
                val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager
                DialogDetail.newInstance(args).show(manager, DialogDetail.TAG)
            }
        }
    }

    fun setData(data: List<Movie>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getData(): List<Movie> = data.filterNotNull()

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_LOADING = 1
        const val VIEW_TYPE_ITEM = 0
    }
}
