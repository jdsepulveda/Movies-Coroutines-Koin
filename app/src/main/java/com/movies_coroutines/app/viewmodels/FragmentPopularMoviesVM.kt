package com.movies_coroutines.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies_coroutines.BuildConfig
import com.movies_coroutines.app.utils.Event
import com.movies_coroutines.app.utils.EventTypes
import com.movies_coroutines.app.utils.Resource
import com.movies_coroutines.remote.model.Movie
import com.movies_coroutines.source.RemoteDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentPopularMoviesVM @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {

    private val popularMoviesList = MutableLiveData<Resource<List<Movie>>>()
    val popularMovies: LiveData<Resource<List<Movie>>>
        get() = popularMoviesList

    private val eventTypes = MutableLiveData<Event<EventTypes>>()
    val event: LiveData<Event<EventTypes>>
        get() = eventTypes

    init {
        popularMoviesList.postValue(Resource.loading())
        viewModelScope.launch {
            try {
                popularMoviesList.postValue(
                    Resource.success(remoteDataSource.getPopularMovies(BuildConfig.API_KEY).results)
                )
            } catch (e: Throwable) {
                popularMoviesList.postValue(Resource.error(e.localizedMessage))
            }
        }
    }
}