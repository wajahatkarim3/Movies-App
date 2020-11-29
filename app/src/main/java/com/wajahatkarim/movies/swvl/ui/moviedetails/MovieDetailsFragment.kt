package com.wajahatkarim.movies.swvl.ui.moviedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil
import com.wajahatkarim.movies.swvl.R
import com.wajahatkarim.movies.swvl.base.BaseFragment
import com.wajahatkarim.movies.swvl.databinding.MovieDetailsFragmentBinding
import com.wajahatkarim.movies.swvl.databinding.PictureItemLayoutBinding
import com.wajahatkarim.movies.swvl.model.ImageModel
import com.wajahatkarim.movies.swvl.model.MovieModel
import com.wajahatkarim.movies.swvl.utils.gone
import com.wajahatkarim.movies.swvl.utils.visible

class MovieDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var bi: MovieDetailsFragmentBinding
    private val photosList = arrayListOf<ImageModel>()
    private lateinit var recyclerAdapter: RecyclerAdapterUtil<ImageModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bi = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProvider).get(MovieDetailsViewModel::class.java)

        var movie = arguments?.getParcelable<MovieModel>("movie")
        if (movie == null) {
            findNavController().popBackStack()
        }

        setupViews()
        initObservations()
        viewModel.init(movie!!)
    }

    fun setupViews() {
        context?.let {
            recyclerAdapter = RecyclerAdapterUtil(it, photosList, R.layout.picture_item_layout)
            recyclerAdapter.addOnDataBindListener { itemView, item, position, _ ->
                var itemBinding = PictureItemLayoutBinding.bind(itemView)
                itemBinding.imgPicture.load(item.getImageUrl()) {
                    crossfade(true)
                }
            }
            bi.recyclerPhotos.isNestedScrollingEnabled = false
            bi.recyclerPhotos.adapter = recyclerAdapter
        }
    }

    fun initObservations() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ContentState -> {
                    Log.w("wajahat", "initObservations: ContentState")
                    bi.recyclerPhotos.visible()
                }
                is ErrorState -> {
                    Snackbar.make(bi.root, state.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        viewModel.movieModel.observe(viewLifecycleOwner) { movie ->
            bi.apply {
                txtName.text = movie.title
                txtRating.text = "${movie.rating}"
                txtRelease.text = "${movie.year}"
                setupGenresChips(movie.genres)
                setupActors(movie.cast)
            }
        }

        viewModel.photosList.observe(viewLifecycleOwner) { photos ->
            Log.w("wajahat", "initObservations: photoslist ${photos.size}")
            photosList.addAll(photos)
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    fun setupGenresChips(genres: List<String>?) {
        bi.apply {
            if (genres == null) {
                chipGroupGenres.gone()
                lblGenres.gone()
            }
            else {
                genres.forEach {
                    var chip = Chip(context)
                    chip.text = it
                    chipGroupGenres.addView(chip)
                }
            }
        }
    }

    fun setupActors(actors: List<String>?) {
        bi.apply {
            if (actors == null) {
                lblActors.gone()
                txtActors.gone()
            } else {
                actors.forEach {
                    txtActors.append(it + ", ")
                }
            }
        }
    }
}