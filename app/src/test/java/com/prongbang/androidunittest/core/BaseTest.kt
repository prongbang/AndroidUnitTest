package com.prongbang.androidunittest.core

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

}