package com.seosh817.kakaoimagesearch.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.seosh817.kakaoimagesearch.core.common.Dispatcher
import com.seosh817.kakaoimagesearch.core.common.KakaoImageSearchDispatchers
import com.seosh817.kakaoimagesearch.core.common.di.ApplicationScope
import com.seosh817.kakaoimagesearch.core.datastore.AppSettingsSerializer
import com.seosh817.kakaoimagesearch.core.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesAppSettingsDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(KakaoImageSearchDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        appSettingsSerializer: AppSettingsSerializer,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = appSettingsSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            context.dataStoreFile("app_settings.pb")
        }
}
