package com.client.player.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.client.player.databinding.FragmentMediaChooserBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MediaChooserFragment : Fragment() {

    private val viewModel: MediaChooserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Timber.e("*** onCreateView")
        val binding = FragmentMediaChooserBinding.inflate(inflater)
        binding.setViewListeners()
        binding.observeViewModel()
        return binding.root
    }

    private fun FragmentMediaChooserBinding.setViewListeners() {
        button1.setOnClickListener { viewModel.onButton1Clicked() }
        button2.setOnClickListener { viewModel.onButton2Clicked() }
        button3.setOnClickListener { viewModel.onButton3Clicked() }
        button4.setOnClickListener { viewModel.onButton4Clicked() }
    }

    private fun FragmentMediaChooserBinding.observeViewModel() {
        viewModel.playMediaItem1.observe(viewLifecycleOwner) {
            Timber.e("*** button clicked do something in Fragment")
            val action = MediaChooserFragmentDirections.actionMediaChooserFragmentToMediaItemFragment(it)
            findNavController().navigate(action)
        }
    }
}