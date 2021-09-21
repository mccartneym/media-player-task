package uk.co.bishopit.player.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.exoplayer2.util.Util

@Suppress("unused")
internal class ExoPlayerLifecycleObserver(private val playerView: CorePlayerView) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        if (Util.SDK_INT >= 24) {
            playerView.initializePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        playerView.hideSystemUi()
        if ((Util.SDK_INT < 24 || playerView.player == null)) {
            playerView.initializePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        if (Util.SDK_INT < 24) {
            playerView.releasePlayer()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        if (Util.SDK_INT >= 24) {
            playerView.releasePlayer()
        }
    }
}
