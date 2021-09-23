package com.client.player.util

import android.content.Context
import com.client.player.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringProviderImpl@Inject constructor(@ApplicationContext val context: Context): StringProvider {

    private val resources = context.resources

    override fun getMediaUris(): List<String> {
        val uriList: Array<String> = resources.getStringArray(R.array.media_uris)
        return uriList.asList()
    }

    override fun getMediaDescriptions(): List<String> {
        val uriDesc: Array<String> = resources.getStringArray(R.array.media_desc)
        return uriDesc.asList()
    }
}
