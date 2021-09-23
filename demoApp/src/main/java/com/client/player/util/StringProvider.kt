package com.client.player.util

interface StringProvider {

    fun getMediaUris(): List<String>
    fun getMediaDescriptions(): List<String>

}