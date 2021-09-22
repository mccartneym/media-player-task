package com.client.player.mediaitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.client.player.R
import com.client.player.databinding.FragmentMediaChooserBinding
import com.client.player.databinding.FragmentMediaItemBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uk.co.bishopit.player.view.CorePlayerView

@AndroidEntryPoint
class MediaItemFragment: Fragment() {

    private val viewModel: MediaItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMediaItemBinding.inflate(inflater)
        binding.initialisePlayer()
//        binding.setViewListeners()
//        binding.observeViewModel()

        return binding.root
    }


    private fun FragmentMediaItemBinding.initialisePlayer() {
        Timber.e("*** initialising player")

        // TODO - get FLOW back in VM which receives playerView events and can be responsible for hiding sytemUi....??
        playerView.initialise(lifecycle, getString(R.string.media_url_dash))
        Timber.e("*** initialised player")
    }

    private fun FragmentMediaItemBinding.setViewListeners() {
//        button1.setOnClickListener { viewModel.onButton1Clicked() }
//        button2.setOnClickListener { viewModel.onButton2Clicked() }
//        button3.setOnClickListener { viewModel.onButton3Clicked() }
//        button4.setOnClickListener { viewModel.onButton4Clicked() }
    }

//    private fun FragmentMediaChooserBinding.observeViewModel() {
//        viewModel.keyCount.observe(viewLifecycleOwner) { count ->
//            keyCount.text = getString(R.string.key_count, count)
//        }
//        viewModel.disableDeleteKeys.observe(viewLifecycleOwner) {
//            buttonDeleteKeys.isEnabled = false
//        }
//        viewModel.enableFactoryReset.observe(viewLifecycleOwner) {
//            buttonFactoryReset.isEnabled = true
//        }
//    }


}