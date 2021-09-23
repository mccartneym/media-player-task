package com.client.player.ui.playlist

import androidx.lifecycle.*
import com.client.player.data.model.UriMediaItem
import com.client.player.data.repository.MediaItemRepository
import com.client.player.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaChooserViewModel @Inject constructor(private val itemRepository: MediaItemRepository) : ViewModel(), LifecycleObserver {

    val playMediaItem = SingleLiveEvent<String>()
    val displayItemDescriptions = MutableLiveData<List<String>>()

    private lateinit var mediaItemList: List<UriMediaItem>

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        mediaItemList = itemRepository.getList()
        val descriptionList: List<String> = mediaItemList.map { it.description }
        displayItemDescriptions.postValue(descriptionList)
    }

    fun onItemClicked(position: Int) {
        val uri = mediaItemList[position].uri
        playMediaItem.postValue(uri)
    }
}
