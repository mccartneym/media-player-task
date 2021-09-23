package com.client.player.di

import com.client.player.data.repository.GoogleMediaItemRepository
import com.client.player.data.repository.MediaItemRepository
import com.client.player.util.StringProvider
import com.client.player.util.StringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DemoAppModule {

    @Binds
    fun provideMediaItemRepository(impl: GoogleMediaItemRepository): MediaItemRepository

    @Binds
    fun provideStringProvider(impl: StringProviderImpl): StringProvider

}
