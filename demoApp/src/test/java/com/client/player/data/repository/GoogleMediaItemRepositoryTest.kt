package com.client.player.data.repository

import com.client.player.data.model.UriMediaItem
import com.client.player.util.StringProvider
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GoogleMediaItemRepositoryTest {

    private val stringProvider: StringProvider = mockk()
    private lateinit var sut: GoogleMediaItemRepository

    @Before
    fun setUp() {
        sut = GoogleMediaItemRepository(stringProvider)
    }

    @Test
    fun `given mismatching data and descriptions, when retrieving list, return emptyList`() {
        every { stringProvider.getMediaUris() } returns listOf("uri1", "uri2", "uri3")
        every { stringProvider.getMediaDescriptions() } returns listOf("desc1", "desc2")

        val result = sut.getList()

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `given lists of matching sizes, when retrieving list, return correct list`() {
        every { stringProvider.getMediaUris() } returns listOf("uri1", "uri2", "uri3")
        every { stringProvider.getMediaDescriptions() } returns listOf("desc1", "desc2", "desc3")

        val result = sut.getList()

        assertEquals(listOf(ITEM1, ITEM2, ITEM3), result)
    }

    companion object {
        private val ITEM1 = UriMediaItem("uri1", "desc1")
        private val ITEM2 = UriMediaItem("uri2", "desc2")
        private val ITEM3 = UriMediaItem("uri3", "desc3")
    }
}
