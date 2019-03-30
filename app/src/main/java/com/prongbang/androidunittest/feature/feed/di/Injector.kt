package com.prongbang.androidunittest.feature.feed.di

import com.prongbang.androidunittest.feature.feed.data.DefaultFeedDataSource
import com.prongbang.androidunittest.feature.feed.data.DefaultFeedRepository
import com.prongbang.androidunittest.feature.feed.data.FeedDataSource
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import com.prongbang.androidunittest.feature.feed.domain.DefaultGetFeedByIdUseCase
import com.prongbang.androidunittest.feature.feed.domain.DefaultGetFeedUseCase
import com.prongbang.androidunittest.feature.feed.domain.GetFeedByIdUseCase
import com.prongbang.androidunittest.feature.feed.domain.GetFeedUseCase
import com.prongbang.androidunittest.feature.feed.presenter.FeedViewModelFactory

object Injector {

    // Provide DataSource
    fun provideFeedDataSource(): FeedDataSource = DefaultFeedDataSource()

    // Provide Repository
    fun provideFeedRepository(): FeedRepository = DefaultFeedRepository(provideFeedDataSource())

    // Provide UseCase
    fun provideGetFeedUseCase() : GetFeedUseCase = DefaultGetFeedUseCase(provideFeedRepository())

    fun provideGetFeedByIdUseCase(): GetFeedByIdUseCase = DefaultGetFeedByIdUseCase(provideFeedRepository())

    // Provide ViewModel
    fun provideFeedViewModelFactory() = FeedViewModelFactory(provideGetFeedUseCase(), provideGetFeedByIdUseCase())

}