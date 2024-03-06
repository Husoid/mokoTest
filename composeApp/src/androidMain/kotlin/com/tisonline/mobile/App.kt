package com.tisonline.mobile

import android.app.Application
import utils.PlatformConfiguration

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }
}

fun App.initPlatformSDK() =
    PlatformSDK.init(
        configuration = PlatformConfiguration(androidContext = applicationContext)
    )