package com.wajahatkarim.movies.swvl.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wajahatkarim.movies.swvl.R
import com.wajahatkarim.movies.swvl.base.BaseFragment
import com.wajahatkarim.movies.swvl.databinding.MovieDetailsFragmentBinding

class MovieDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var bi: MovieDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bi = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProvider).get(MovieDetailsViewModel::class.java)
    }

}