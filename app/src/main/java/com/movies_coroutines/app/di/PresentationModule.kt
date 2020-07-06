package com.movies_coroutines.app.di

import com.movies_coroutines.app.viewmodels.FragmentLatestMoviesVM
import com.movies_coroutines.app.viewmodels.FragmentPopularMoviesVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { FragmentPopularMoviesVM(get()) }
    viewModel { FragmentLatestMoviesVM(get()) }
}