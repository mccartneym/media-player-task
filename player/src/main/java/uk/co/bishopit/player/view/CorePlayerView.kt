package uk.co.bishopit.player.view

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.Lifecycle
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes
import timber.log.Timber
import uk.co.bishopit.player.util.getMimeType
import uk.co.bishopit.player.util.hideSystemUi

class CorePlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr) {

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    private var uri: String = ""

    private val playbackStateListener: Player.EventListener = object : Player.EventListener {
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

    fun initialise(lifecycle: Lifecycle, uri: String) {
        Timber.i("initialise")
        lifecycle.addObserver(PlayerLifecycleObserver(this))
        this.uri = uri
    }

    internal fun preparePlayer() {
        Timber.i("preparePlayer")
        val trackSelector = DefaultTrackSelector(context).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = SimpleExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                val mediaItem = when (getMimeType(context, uri)) {
                    "video/mp4",
                    "audio/mpeg" -> MediaItem.fromUri(uri)
                    else -> {
                        MediaItem.Builder()
                            .setUri(uri)
                            .setMimeType(MimeTypes.APPLICATION_MPD)
                            .build()
                    }
                }
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }
    }

    internal fun releasePlayer() {
        Timber.i("releasePlayer")
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)
            release()
        }
        player = null
    }

    fun hideSystemUi() {
        Timber.i("hideSystemUi")
        hideSystemUi(context, this)
    }
}
