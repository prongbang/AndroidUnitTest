package com.prongbang.androidunittest.feature.feed.data

import android.content.Context
import com.prongbang.androidunittest.R

interface FeedRepository {

    fun getAppName(): String

}

class DefaultFeedRepository constructor(private val mContext: Context) : FeedRepository {

    override fun getAppName(): String {
        return mContext.getString(R.string.app_name)
    }

}