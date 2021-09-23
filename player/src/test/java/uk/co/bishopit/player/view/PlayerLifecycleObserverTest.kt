package uk.co.bishopit.player.view

import io.mockk.Called
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test


class PlayerLifecycleObserverTest {

    private val playerView: CorePlayerView = mockk(relaxed = true)

    private lateinit var sut: PlayerLifecycleObserver

    private fun setUp(sdkVersion: Int) {
        sut = PlayerLifecycleObserver(sdkVersion)
        sut.setPlayer(playerView)
    }

    @Test
    fun `given Marshmallow device, when lifecycle starts, then playerView is not called`() {
        setUp(MARSHMALLOW)

        sut.onStart()

        verify { playerView wasNot Called }
    }

    @Test
    fun `given Nougat device, when lifecycle starts, then playerView is prepared`() {
        setUp(NOUGAT)

        sut.onStart()

        verify(exactly = 1) { playerView.preparePlayer() }
    }

    @Test
    fun `given Marshmallow device, when lifecycle resumes, then system ui is hidden`() {
        setUp(MARSHMALLOW)

        sut.onResume()

        verify(exactly = 1) { playerView.hideSystemUi() }
    }

    @Test
    fun `given Nougat device, when lifecycle resumes, then system ui is hidden`() {
        setUp(NOUGAT)

        sut.onResume()

        verify(exactly = 1) { playerView.hideSystemUi() }
    }

    @Test
    fun `given Marshmallow device, when lifecycle resumes, then playerView is prepared`() {
        setUp(MARSHMALLOW)

        sut.onResume()

        verify(exactly = 1) { playerView.preparePlayer() }
    }

    @Test
    fun `given Nougat device, when lifecycle resumes, then playerView is not prepared`() {
        setUp(NOUGAT)

        sut.onResume()

        verify(exactly = 0) { playerView.preparePlayer() }
    }

    @Test
    fun `given Marshmallow device, when lifecycle pauses, then system ui is shown`() {
        setUp(MARSHMALLOW)

        sut.onPause()

        verify(exactly = 1) { playerView.showSystemUi() }
    }

    @Test
    fun `given Nougat device, when lifecycle pauses, then system ui is shown`() {
        setUp(NOUGAT)

        sut.onPause()

        verify(exactly = 1) { playerView.showSystemUi() }
    }

    @Test
    fun `given Marshmallow device, when lifecycle pauses, then playerView is released`() {
        setUp(MARSHMALLOW)

        sut.onPause()

        verify(exactly = 1) { playerView.releasePlayer() }
    }

    @Test
    fun `given Nougat device, when lifecycle pauses, then playerView is not released`() {
        setUp(NOUGAT)

        sut.onPause()

        verify(exactly = 0) { playerView.releasePlayer() }
    }

    @Test
    fun `given Marshmallow device, when lifecycle stops, then playerView is not released`() {
        setUp(MARSHMALLOW)

        sut.onStop()

        verify(exactly = 0) { playerView.releasePlayer() }
    }

    @Test
    fun `given Nougat device, when lifecycle stops, then playerView is released`() {
        setUp(NOUGAT)

        sut.onStop()

        verify(exactly = 1) { playerView.releasePlayer() }
    }


    companion object {
        private const val MARSHMALLOW = 23
        private const val NOUGAT = 24
    }
}