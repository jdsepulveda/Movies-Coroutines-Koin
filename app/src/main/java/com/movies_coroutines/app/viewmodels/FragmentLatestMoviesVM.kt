package com.movies_coroutines.app.viewmodels

import androidx.lifecycle.ViewModel
import com.movies_coroutines.source.RemoteDataSource
import javax.inject.Inject

class FragmentLatestMoviesVM @Inject constructor(
private val remoteDataSource: RemoteDataSource
) : ViewModel() {

}