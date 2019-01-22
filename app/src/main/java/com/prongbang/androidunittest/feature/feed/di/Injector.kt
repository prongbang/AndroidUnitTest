package com.prongbang.androidunittest.feature.feed.di

import android.content.Context
import com.prongbang.androidunittest.feature.feed.data.DefaultFeedRepository
import com.prongbang.androidunittest.feature.feed.domain.GetAppNameUseCase
import com.prongbang.androidunittest.feature.feed.presenter.FeedViewModelFactory

object Injector {

    // Provide Repository
    fun provideFeedRepository(context: Context) = DefaultFeedRepository(context)

    // Provide UseCase
    fun provideGetAppNameUseCase(context: Context) = GetAppNameUseCase(provideFeedRepository(context))

    // Provide ViewModel
    fun provideFeedViewModelFactory(context: Context) = FeedViewModelFactory(provideGetAppNameUseCase(context))

}