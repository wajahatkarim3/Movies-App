package com.wajahatkarim.movies.swvl.ui.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wajahatkarim.movies.swvl.R
import com.wajahatkarim.movies.swvl.base.BaseFragment
import com.wajahatkarim.movies.swvl.databinding.MoviesListFragmentBinding

class MoviesListFragment : BaseFragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var bi: MoviesListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bi = MoviesListFragmentBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesListViewModel::class.java)
    }

}