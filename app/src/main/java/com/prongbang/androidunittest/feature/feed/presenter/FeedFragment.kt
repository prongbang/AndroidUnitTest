package com.prongbang.androidunittest.feature.feed.presenter

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.prongbang.androidunittest.R
import com.prongbang.androidunittest.feature.feed.di.Injector

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = Injector.provideFeedViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(FeedViewModel::class.java)

        getFeed()
    }

    private fun getFeed() {
        viewModel.getFeeds().observe(this, Observer {
            Log.i("getFeed", "$it")
        })
    }

}
