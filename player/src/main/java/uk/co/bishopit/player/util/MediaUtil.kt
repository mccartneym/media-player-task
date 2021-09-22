package uk.co.bishopit.player.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import java.util.*

fun getMimeType(context: Context, uri: String): String? {
    val uriObject = Uri.parse(uri)
    val mimeType: String? = if (ContentResolver.SCHEME_CONTENT == uriObject.scheme) {
        val cr: ContentResolver = context.contentResolver
        cr.getType(uriObject)
    } else {
        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri)
        MimeTypeMap.getSingleton().getMimeTypeFromExtension(
            fileExtension.lowercase(Locale.getDefault())
        )
    }
    return mimeType
}
