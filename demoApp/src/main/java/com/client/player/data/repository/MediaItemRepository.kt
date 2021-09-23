package com.client.player.data.repository

import com.client.player.data.model.UriMediaItem

interface MediaItemRepository {
    fun getList(): List<UriMediaItem>
}