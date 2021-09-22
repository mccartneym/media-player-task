package com.client.player.ui.mediaitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.client.player.databinding.FragmentMediaItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaItemFragment : Fragment() {

    private val viewModel: MediaItemViewModel by viewModels()
    private val args: MediaItemFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMediaItemBinding.inflate(inflater)
        binding.initialisePlayer()
        return binding.root
    }


    private fun FragmentMediaItemBinding.initialisePlayer() {
        val uri = args.mediaUri
        playerView.initialise(lifecycle, uri)
    }
}
