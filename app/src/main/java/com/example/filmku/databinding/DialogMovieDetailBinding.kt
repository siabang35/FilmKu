// DialogMovieDetailBinding.kt
package com.example.filmku.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

object DialogMovieDetailBinding {

    fun inflate(inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean): DialogMovieDetailBinding {
        return inflate(inflater, container, attachToParent)
    }

    fun bind(dialogFragment: DialogFragment): DialogMovieDetailBinding {
        return bind(dialogFragment.requireView())
    }
}
