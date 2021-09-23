package uk.co.bishopit.player.di

import com.google.android.exoplayer2.extractor.ts.TsExtractor
import com.google.android.exoplayer2.util.Util
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.co.bishopit.player.view.CorePlayerView
import uk.co.bishopit.player.view.PlayerLifecycleObserver
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class PlayerModule {

    @Provides
    @Named("SdkInt")
    fun provideSdkInt(): Int {
        return Util.SDK_INT
    }
}
