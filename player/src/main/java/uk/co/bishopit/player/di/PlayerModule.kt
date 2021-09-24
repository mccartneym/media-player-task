package uk.co.bishopit.player.di

import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
