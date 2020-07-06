package com.movies_coroutines.remote.api

import com.movies_coroutines.remote.model.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): ResponseWrapper
    //suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<ResponseWrapper>
}