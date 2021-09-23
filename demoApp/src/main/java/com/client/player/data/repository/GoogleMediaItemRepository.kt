package com.client.player.data.repository

import com.client.player.data.model.UriMediaItem
import com.client.player.util.StringProvider
import timber.log.Timber
import javax.inject.Inject

class GoogleMediaItemRepository @Inject constructor(private val stringProvider: StringProvider) : MediaItemRepository {

    override fun getList(): List<UriMediaItem> {
        val uriList = stringProvider.getMediaUris()
        val uriDesc = stringProvider.getMediaDescriptions()

        return if (uriList.size == uriDesc.size) {
            uriList.mapIndexed { index, item ->
                UriMediaItem(item, uriDesc[index])
            }
        } else {
            Timber.e("Uri resources and descriptions do not match")
            emptyList()
        }
    }
}
