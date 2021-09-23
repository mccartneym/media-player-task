package uk.co.bishopit.player.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import javax.inject.Inject
import javax.inject.Named

@Suppress("unused")
internal class PlayerLifecycleObserver @Inject constructor(@Named("SdkInt") var sdkInt: Int) : LifecycleObserver {

    private var playerView: CorePlayerView? = null

    fun setPlayer(playerView: CorePlayerView) {
        this.playerView = playerView
    }

    private fun releasePlayer() {
        playerView?.releasePlayer()
        playerView = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        if (sdkInt >= 24) {
            playerView?.preparePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        playerView?.hideSystemUi()
        if ((sdkInt < 24 || playerView?.player == null)) {
            playerView?.preparePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        playerView?.showSystemUi()
        if (sdkInt < 24) {
            releasePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        if (sdkInt >= 24) {
            releasePlayer()
        }
    }
}
