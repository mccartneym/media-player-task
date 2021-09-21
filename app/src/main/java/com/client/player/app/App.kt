package com.client.player.app;

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uk.co.bishopit.player.setup.Initialiser
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var playerInitialiser: Initialiser

    override fun onCreate() {
        super.onCreate()

        initPlayerLibrary()
    }

    private fun initPlayerLibrary() {
        playerInitialiser.start()
        Timber.e("*** library initialised")
    }
}
