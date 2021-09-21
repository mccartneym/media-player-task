package uk.co.bishopit.player.setup

import timber.log.Timber
import javax.inject.Inject

class Initialiser @Inject constructor() {

    fun start() {
        initLogging()

        Timber.i("Player initialised")
    }

    private fun initLogging() {
        Timber.plant(Timber.DebugTree())
    }
}
