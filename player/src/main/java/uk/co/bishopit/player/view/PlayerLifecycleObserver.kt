package uk.co.bishopit.player.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.exoplayer2.util.Util
import javax.inject.Inject

@Suppress("unused")
internal class PlayerLifecycleObserver @Inject constructor() : LifecycleObserver {

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
        if (Util.SDK_INT >= 24) {
            playerView?.preparePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        playerView?.hideSystemUi()
        if ((Util.SDK_INT < 24 || playerView?.player == null)) {
            playerView?.preparePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }
}
