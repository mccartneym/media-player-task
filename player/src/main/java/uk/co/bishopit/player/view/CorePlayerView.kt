package uk.co.bishopit.player.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes
import timber.log.Timber
import uk.co.bishopit.player.R

class CorePlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr) {

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    private val playbackStateListener: Player.EventListener = playbackStateListener()

    fun initialise(lifecycle: Lifecycle) {
        lifecycle.addObserver(ExoPlayerLifecycleObserver(this))
    }

    fun initializePlayer() {
        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = SimpleExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                val mediaItem = MediaItem.Builder()
                    .setUri(context.getString(R.string.media_url_dash))
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()

                exoPlayer.setMediaItem(mediaItem)
//                val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))
//                val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
//                exoPlayer.addMediaItem(mediaItem)
//                val secondMediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3));
//                exoPlayer.addMediaItem(secondMediaItem);
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }
    }

    fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)
            release()
        }
        player = null
    }

    private fun playbackStateListener() = object : Player.EventListener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val state: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "Idle"
                ExoPlayer.STATE_BUFFERING -> "Buffering"
                ExoPlayer.STATE_READY -> "Ready"
                ExoPlayer.STATE_ENDED -> "Ended"
                else -> "Unknown"
            }
            Timber.d("Player state: $state")
        }
    }

    fun hideSystemUi() {
        val window = (context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, this).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI() {
        val window = (context as Activity).window
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, this).show(WindowInsetsCompat.Type.systemBars())
    }

}
