package com.client.player.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.client.player.R
import uk.co.bishopit.player.view.CorePlayerView

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initialisePlayer()
    }

    private fun initialisePlayer() {
        val playerView: CorePlayerView = findViewById(R.id.video_view)
        playerView.initialise(lifecycle)
    }
}
