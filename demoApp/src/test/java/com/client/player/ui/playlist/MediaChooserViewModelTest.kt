package com.client.player.ui.playlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.client.player.data.model.UriMediaItem
import com.client.player.data.repository.MediaItemRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MediaChooserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val itemRepository: MediaItemRepository = mockk()
    private val itemDescriptionsObserver: Observer<List<String>> = mockk(relaxed = true)
    private val itemUriObserver: Observer<String> = mockk(relaxed = true)

    private lateinit var sut: MediaChooserViewModel


    @Before
    fun setUp() {
        sut = MediaChooserViewModel(itemRepository)
        sut.itemDescriptions.observeForever(itemDescriptionsObserver)
        sut.playMediaItem.observeForever(itemUriObserver)
        every { itemRepository.getList() } returns MEDIA_ITEM_LIST
    }

    @Test
    fun `given a list of UriMediaItems, when the VM starts, then send list of descriptions to the view`() {
        val slot = slot<List<String>>()

        sut.onStart()

        verify { itemDescriptionsObserver.onChanged(capture(slot)) }
        assertEquals(MEDIA_DESCRIPTION_LIST, slot.captured)
    }

    @Test
    fun `given a list of UriMediaItems, when first is selected, then the respective uri is sent to the view`() {
        val slot = slot<String>()
        sut.onStart()

        sut.onItemClicked(0)

        verify { itemUriObserver.onChanged(capture(slot)) }
        assertEquals("uri1", slot.captured)
    }

    @Test
    fun `given a list of UriMediaItems, when second is selected, then the respective uri is sent to the view`() {
        val slot = slot<String>()
        sut.onStart()

        sut.onItemClicked(1)

        verify { itemUriObserver.onChanged(capture(slot)) }
        assertEquals("uri2", slot.captured)
    }

    @Test
    fun `given a list of UriMediaItems, when third is selected, then the respective uri is sent to the view`() {
        val slot = slot<String>()
        sut.onStart()

        sut.onItemClicked(2)

        verify { itemUriObserver.onChanged(capture(slot)) }
        assertEquals("uri3", slot.captured)
    }

    companion object {
        private val MEDIA_ITEM_LIST = listOf<UriMediaItem>(
            UriMediaItem("uri1", "desc1"),
            UriMediaItem("uri2", "desc2"),
            UriMediaItem("uri3", "desc3")
        )
        private val MEDIA_DESCRIPTION_LIST = listOf<String>("desc1", "desc2", "desc3")
    }
}