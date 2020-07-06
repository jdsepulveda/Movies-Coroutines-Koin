package com.movies_coroutines.app.di

import com.movies_coroutines.BuildConfig
import com.movies_coroutines.remote.api.MoviesService
import com.movies_coroutines.source.RemoteDataSource
import com.movies_coroutines.source.RemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteSourceModule = module {

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(MoviesService::class.java)
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }
}