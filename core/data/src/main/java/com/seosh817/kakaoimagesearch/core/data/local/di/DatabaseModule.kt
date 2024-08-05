package com.seosh817.kakaoimagesearch.core.data.local.di

import android.content.Context
import androidx.room.Room
import com.seosh817.kakaoimagesearch.core.data.local.KakaoImageSearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesKakaoImageSearchDatabase(
        @ApplicationContext context: Context,
    ): KakaoImageSearchDatabase = Room
        .databaseBuilder(
            context = context,
            klass = KakaoImageSearchDatabase::class.java,
            name = KakaoImageSearchDatabase.DATABASE_NAME,
        )
        .build()
}