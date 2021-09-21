package uk.co.bishopit.player.setup

import timber.log.Timber
import javax.inject.Inject

class Initialiser @Inject constructor() {

    fun start() {
        initLogging()
    }

    private fun initLogging() {
        Timber.plant(Timber.DebugTree())
        Timber.d("Logging initialised")
    }
}
