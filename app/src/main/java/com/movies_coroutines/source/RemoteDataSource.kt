package com.movies_coroutines.source

import com.movies_coroutines.remote.model.ResponseWrapper

interface RemoteDataSource {

    suspend fun getPopularMovies(apiKey: String): ResponseWrapper
}