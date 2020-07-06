package com.movies_coroutines.source

import com.movies_coroutines.remote.api.MoviesService
import com.movies_coroutines.remote.model.ResponseWrapper
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
) : RemoteDataSource {

    override suspend fun getPopularMovies(apiKey: String): ResponseWrapper {
        return moviesService.getPopularMovies(apiKey)
    }
}