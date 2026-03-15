package org.example.white

import android.app.Application
import com.example.common.platform_provider.AppContext

class WhitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppContext.setUp(applicationContext)
    }
}