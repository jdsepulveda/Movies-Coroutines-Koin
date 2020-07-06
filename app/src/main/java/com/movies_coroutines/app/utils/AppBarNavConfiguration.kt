package com.movies_coroutines.app.utils

import androidx.navigation.ui.AppBarConfiguration
import com.movies_coroutines.R

private val topLevelIdsDestination: Set<Int> = setOf(
    R.id.fragmentPopularMovies,
    R.id.fragmentLatestMovies
)
val appBarNavConfiguration = AppBarConfiguration(topLevelIdsDestination)