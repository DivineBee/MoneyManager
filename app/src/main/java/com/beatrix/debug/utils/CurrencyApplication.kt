package com.beatrix.debug.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// add dagger-hilt injection also added it to the manifest file for it to work
@HiltAndroidApp
class CurrencyApplication : Application()