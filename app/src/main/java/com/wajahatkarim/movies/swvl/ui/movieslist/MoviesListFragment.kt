package com.wajahatkarim.movies.swvl.ui.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil
import com.wajahatkarim.movies.swvl.MainActivity
import com.wajahatkarim.movies.swvl.R
import com.wajahatkarim.movies.swvl.base.BaseFragment
import com.wajahatkarim.movies.swvl.databinding.ItemMovieLayoutBinding
import com.wajahatkarim.movies.swvl.databinding.MoviesListFragmentBinding
import com.wajahatkarim.movies.swvl.model.MovieModel
import com.wajahatkarim.movies.swvl.ui.moviedetails.MovieDetailsFragment
import com.wajahatkarim.movies.swvl.utils.gone
import com.wajahatkarim.movies.swvl.utils.visible

class MoviesListFragment : BaseFragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var bi: MoviesListFragmentBinding
    private lateinit var recyclerAdapter: RecyclerAdapterUtil<MovieModel>
    val moviesList = arrayListOf<MovieModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bi = MoviesListFragmentBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProvider).get(MoviesListViewModel::class.java)

        setupViews()
        initObservations()
        viewModel.init()
    }

    fun setupViews() {
        context?.let {
            // RecyclerView
            recyclerAdapter = RecyclerAdapterUtil(it, moviesList, R.layout.item_movie_layout)
            recyclerAdapter.addOnDataBindListener { itemView, item, position, _ ->
                var itemBinding = ItemMovieLayoutBinding.bind(itemView)
                itemBinding.txtName.text = item.title
                itemBinding.txtRelease.text = "${item.year}"
                itemBinding.txtRating.text = "${item.rating}"
            }
            recyclerAdapter.addOnClickListener { item, position ->
                var bundle = bundleOf("movie" to item)
                (requireActivity() as MainActivity).navController.navigate(R.id.action_movies_to_movies_details, bundle)
            }
            bi.recyclerMovies.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            bi.recyclerMovies.adapter = recyclerAdapter
        }
    }

    fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is LoadingState -> {
                    bi.apply {
                        recyclerMovies.gone()
                        txtError.gone()
                        progressBar.visible()
                    }
                }

                is ReadAssetsState -> {
                    viewModel.readMoviesDataFromAssets(requireActivity().assets.open("movies.json"))
                }

                is ContentState -> {
                    bi.apply {
                        recyclerMovies.visible()
                        txtError.gone()
                        progressBar.gone()
                    }
                }

                is EmptyState -> {
                    bi.apply {
                        recyclerMovies.gone()
                        txtError.visible()
                        txtError.text = getString(R.string.no_movies_found_str)
                        progressBar.gone()
                    }
                }

                is ErrorState -> {
                    bi.apply {
                        recyclerMovies.gone()
                        txtError.visible()
                        txtError.text = state.message
                        progressBar.gone()
                    }
                }
            }
        }

        viewModel.moviesList.observe(viewLifecycleOwner) { movies ->
            moviesList.clear()
            moviesList.addAll(movies)
            recyclerAdapter.notifyDataSetChanged()
        }
    }
}