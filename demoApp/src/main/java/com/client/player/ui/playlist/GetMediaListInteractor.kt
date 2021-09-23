package com.client.player.ui.playlist

import com.client.player.data.model.UriMediaItem
import com.client.player.data.repository.MediaItemRepository
import javax.inject.Inject

class GetMediaListInteractor @Inject constructor(private val itemRepository: MediaItemRepository) {

    fun getMediaList(): List<UriMediaItem> {
        return itemRepository.getList()
    }
}
