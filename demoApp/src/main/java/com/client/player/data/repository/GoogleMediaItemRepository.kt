package com.client.player.data.repository

import android.content.Context
import com.client.player.R
import com.client.player.data.model.UriMediaItem
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class GoogleMediaItemRepository @Inject constructor(@ApplicationContext val context: Context) : MediaItemRepository {

    override fun getList(): List<UriMediaItem> {
        val uriList: Array<String> = context.resources.getStringArray(R.array.media_uris)
        val uriDesc: Array<String> = context.resources.getStringArray(R.array.media_desc)

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
