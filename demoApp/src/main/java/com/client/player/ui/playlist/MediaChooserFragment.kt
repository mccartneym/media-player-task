package com.client.player.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.client.player.databinding.FragmentMediaChooserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaChooserFragment : Fragment() {

    private val viewModel: MediaChooserViewModel by viewModels()

    private val clickListener: MediaChooserAdapter.ItemClickListener = object : MediaChooserAdapter.ItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            viewModel.onItemClicked(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMediaChooserBinding.inflate(inflater)
        binding.observeViewModel()
        return binding.root
    }

    private fun FragmentMediaChooserBinding.observeViewModel() {
        viewModel.displayItemDescriptions.observe(viewLifecycleOwner) {
            mediaItemList.adapter = MediaChooserAdapter(it, clickListener)
        }
        viewModel.playMediaItem.observe(viewLifecycleOwner) {
            val action = MediaChooserFragmentDirections.actionMediaChooserFragmentToMediaItemFragment(it)
            findNavController().navigate(action)
        }
    }
}