package com.movies_coroutines.app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.movies_coroutines.R
import com.movies_coroutines.app.adapter.PopularMoviesAdapter
import com.movies_coroutines.app.utils.Status
import com.movies_coroutines.app.utils.appBarNavConfiguration
import com.movies_coroutines.app.utils.extensions.gone
import com.movies_coroutines.app.utils.extensions.visible
import com.movies_coroutines.app.viewmodels.FragmentPopularMoviesVM
import com.movies_coroutines.databinding.FragmentPopularMoviesBinding
import com.movies_coroutines.remote.model.Movie
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentPopularMovies : Fragment() {

    private val fragmentPopularMoviesVM: FragmentPopularMoviesVM by viewModel()

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, R.layout.fragment_popular_movies, container, false
        ).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.findBinding<FragmentPopularMoviesBinding>(view)?.apply {
            viewModel = fragmentPopularMoviesVM
            lifecycleOwner = viewLifecycleOwner
        }

        NavigationUI.setupWithNavController(
            popular_movies_toolbar,
            findNavController(),
            appBarNavConfiguration
        )

        initRecyclerView()
        setUpDataObservers()
    }

    private fun initRecyclerView() {
        popularMoviesAdapter = PopularMoviesAdapter { movieItem: Movie -> movieItemClicked(movieItem) }
        rvPopularMovies.adapter = popularMoviesAdapter
    }

    private fun movieItemClicked(movieItem: Movie) {
        Log.d("In movieItemClicked", movieItem.overview)
    }

    private fun setUpDataObservers() {
        fragmentPopularMoviesVM.popularMovies.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.LOADING -> {
                    progressBarLoading.visible()
                }
                Status.SUCCESS -> {
                    progressBarLoading.gone()
                    it.data.orEmpty().let { movies ->
                        Log.d("Movie List", movies.size.toString())
                        popularMoviesAdapter.populate(movies)
                    }
                }
                Status.ERROR -> {
                    progressBarLoading.gone()
                }
            }
        })
    }
}