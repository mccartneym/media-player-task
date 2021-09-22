package com.client.player.playlist

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.client.player.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MediaChooserViewModel @Inject constructor() : ViewModel(), LifecycleObserver {

    val playMediaItem1 = SingleLiveEvent<String>()
    val playMediaItem2 = SingleLiveEvent<String>()
    val playMediaItem3 = SingleLiveEvent<String>()
    val playMediaItem4 = SingleLiveEvent<String>()


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Timber.e("*** onStart...")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Timber.e("*** onStop")
    }

    fun onButton1Clicked() {
        Timber.e("*** onButton1Clicked")
        playMediaItem1.postValue("https://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0")
    }

    fun onButton2Clicked() {

    }

    fun onButton3Clicked() {

    }

    fun onButton4Clicked() {

    }
}
