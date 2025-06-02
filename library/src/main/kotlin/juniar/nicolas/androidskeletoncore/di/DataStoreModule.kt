package juniar.nicolas.androidskeletoncore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import juniar.nicolas.androidskeletoncore.GenericDataStoreHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideGenericDataStoreHelper(@ApplicationContext context: Context): GenericDataStoreHelper =
        GenericDataStoreHelper(context)
}