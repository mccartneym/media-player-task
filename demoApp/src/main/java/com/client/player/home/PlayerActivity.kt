package com.client.player.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.client.player.R
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bishopit.player.view.CorePlayerView

@AndroidEntryPoint
class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
    }
}
